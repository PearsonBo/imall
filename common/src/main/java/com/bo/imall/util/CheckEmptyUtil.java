package com.bo.imall.util;

import com.bo.imall.exception.BizException;
import com.bo.imall.exception.ExceptionType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author HuJianbo
 */
public final class CheckEmptyUtil {

    private CheckEmptyUtil() {

    }

    /**
     * string 是否存在
     */
    public static void checkString(String param, String error) {
        checkString(param, error, ExceptionType.VIOLATE_BIZ_CHECK);
    }

    /**
     * string 是否存在
     *
     * @param type
     * @param param
     * @param error
     */
    public static void checkString(String param, String error, ExceptionType type) {
        if (!existString(param)) {
            throw new BizException(error, type);
        }
    }

    /**
     * date 是否存在
     */
    public static void checkDate(Date param, String error) {
        checkDate(ExceptionType.VIOLATE_BIZ_CHECK, param, error);
    }

    /**
     * date 是否存在
     */
    public static void checkDate(ExceptionType type, Date param, String error) {
        checkObject(type, param, error);
    }

    /**
     * long 是否存在
     *
     * @checkLong param
     */
    public static void checkLong(Long param, String error) {
        checkLong(ExceptionType.VIOLATE_BIZ_CHECK, param, error);
    }

    /**
     * long 是否存在
     *
     * @checkLong param
     */
    public static void checkLong(ExceptionType type, Long param, String error) {
        checkObject(type, param, error);
    }

    /**
     * Integer 是否存在
     */
    public static void checkInteger(Integer param, String error) {
        checkInteger(ExceptionType.VIOLATE_BIZ_CHECK, param, error);
    }

    /**
     * Integer 是否存在
     */
    public static void checkInteger(ExceptionType type, Integer param, String error) {
        checkObject(type, param, error);
    }

    /**
     * 检查对象是否存在
     */
    public static void checkObject(Object object, String error) {
        checkObject(ExceptionType.VIOLATE_BIZ_CHECK, object, error);
    }

    /**
     * 检查对象是否存在
     */
    public static void checkObject(ExceptionType type, Object object, String error) {
        if (!existObject(object)) {
            throw new BizException(error, type);
        }
    }

    /**
     * 检查对象是否存在
     */
    public static void checkEnum(Enum param, String error) {
        checkEnum(ExceptionType.VIOLATE_BIZ_CHECK, param, error);
    }

    /**
     * 检查对象是否存在
     */
    public static void checkEnum(ExceptionType type, Enum param, String error) {
        if (!existEnum(param)) {
            throw new BizException(error, type);
        }
    }

    /**
     * 检查多个对象是否存在
     *
     * @param error   错误信息
     * @param objects 对象集合
     */
    public static void checkObjectMultipleOr(String error, Object... objects) {
        checkObjectMultipleOr(ExceptionType.VIOLATE_BIZ_CHECK, error, objects);
    }

    /**
     * 批量检查Object对象是否为空
     */
    public static void checkObjectMultipleOr(ExceptionType type, String error, Object... objects) {
        for (Object object : objects) {
            if (!existObject(object)) {
                throw new BizException(error, type);
            }
        }
    }

    /**
     * 检查list是否存在
     */
    public static void checkList(String error, List list) {
        checkList(error, ExceptionType.VIOLATE_BIZ_CHECK, list);
    }

    /**
     * 检查list是否存在
     */
    public static void checkList(String error, ExceptionType type, List list) {
        if (!existList(list)) {
            throw new BizException(error, type);
        }
    }

    /**
     * 检查多个 and 是否存在
     */
    public static void checkStringMultipleAnd(String error, String... params) {
        checkStringMultipleAnd(error, ExceptionType.VIOLATE_BIZ_CHECK, params);
    }

    /**
     * 检查多个 and 是否存在
     */
    public static void checkStringMultipleAnd(String error, ExceptionType type, String... params) {
        int count = params.length;
        for (String param : params) {
            if (!existString(param)) {
                count--;
            }
        }
        if (count == 0) {
            throw new BizException(error, type);
        }
    }

    /**
     * 检查多个 and 是否存在
     */
    public static void checkObjectMultipleAnd(String error, Object... params) {
        checkObjectMultipleAnd(error, ExceptionType.VIOLATE_BIZ_CHECK, params);
    }

    /**
     * object 是否存在
     */
    private static boolean existObject(Object param) {
        if (param == null) {
            return false;
        }
        return true;
    }

    /**
     * object 是否存在
     */
    private static boolean existEnum(Enum param) {
        if (param == null) {
            return false;
        }
        return true;
    }

    /**
     * string 是否存在
     */
    private static boolean existString(String param) {
        if (StringUtils.isEmpty(param)) {
            return false;
        }
        return true;
    }

    /**
     * 检查list是否存在
     */
    private static boolean existList(List list) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        return true;
    }

    /**
     * boolean
     */
    public static boolean existBoolean(Boolean param) {
        if (param != null && param) {
            return true;
        }
        return false;
    }

}
