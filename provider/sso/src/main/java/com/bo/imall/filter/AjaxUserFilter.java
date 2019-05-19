package com.bo.imall.filter;

import com.bo.imall.helper.WebHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AjaxUserFilter extends UserFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.getSession() != null) {
            log.info("sessionId:(AjaxUserFilter)" + subject.getSession().getId().toString() + "request:" + ((HttpServletRequest) request).getRequestURI());
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = WebUtils.toHttp(request);
        if (WebHelper.isAjaxRequest(req)) {
            HttpServletResponse res = WebUtils.toHttp(response);
            res.sendError(401);
            return false;
        }
        return super.onAccessDenied(request, response);
    }

}
