package com.bo.imall.filter;

import com.bo.imall.util.AuthorizationUtil;
import com.bo.imall.util.RedisReadHelper;
import com.google.code.kaptcha.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Slf4j
public class CaptchaValidateFilter extends AccessControlFilter {
    private static final String PAGE_CAPTCHA_ENABLED = "pageCaptchaEnabled";

    private static final String POST = "post";

    private static final String CAPTCHA_ENABLED = "captchaEnabled";

    private static final String CAPTCHA_VALIDATE_FAILURE_URL = "/web/user/captchaValidateFailure";

    @Autowired
    private RedisReadHelper redisReadHelper;
    /**
     * 是否开启验证码支持
     */
    private boolean captchaEnabled = true;

    /**
     * 前台提交的验证码参数名
     */
    private String captchaParam = "kaptcha";

    public void setCaptchaEnabled(boolean captchaEnabled) {
        this.captchaEnabled = captchaEnabled;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //1、设置验证码是否开启属性，页面可以根据该属性来决定是否显示验证码
        request.setAttribute(CAPTCHA_ENABLED, captchaEnabled);

        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        //2、判断验证码是否禁用 或不是表单提交（允许访问）
        if (!captchaEnabled || !POST.equalsIgnoreCase(httpServletRequest.getMethod())) {
            return true;
        }
        boolean pageCaptchaEnabled = Boolean.parseBoolean(httpServletRequest.getParameter(PAGE_CAPTCHA_ENABLED));
        if (!pageCaptchaEnabled) {
            return true;
        }
        String sessionId = AuthorizationUtil.getSessionId(request);

        String kaptchaExpected = redisReadHelper.getStringInRedis(Constants.KAPTCHA_SESSION_KEY + sessionId);

        String kaptchaReceived = httpServletRequest.getParameter(captchaParam);

        boolean shouldCheckCaptcha = true;
        if (!shouldCheckCaptcha) {
            log.info("不启用验证码校验");
            return true;
        }

        //3、此时是表单提交，验证验证码是否正确
        return !(kaptchaReceived == null || kaptchaExpected == null || !kaptchaReceived.equalsIgnoreCase(kaptchaExpected));
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.issueRedirect(request, response, CAPTCHA_VALIDATE_FAILURE_URL);
        return false;
    }
}
