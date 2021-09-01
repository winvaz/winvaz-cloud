package com.icore.winvaz.winvazcommon.exception;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/8/11 15:18
 * @Version 1.0.0
 */
public enum ExceptionCodeEnum {

    /**
     * 成功
     */
    EX_SUCCESS(000),

    /**
     * 非法参数
     */
    EX_INVALID_PARAMETER(001),

    /**
     * 权限不足
     */
    EX_PERMISSION_DENIED(403),

    /**
     * 暂未登录或token已经过期
     */
    UNAUTHORIZED(401),

    /**
     * 用户不存在
     */
    EX_USER_NOT_FOUND(10001),

    /**
     * 您的账号未认证，请先处理
     */
    EX_DRIVER_UNCERTIFIED(12009),

    /**
     * 平台密钥未配置
     */
    EX_PLATFORM_SECRETKEY_NOTEXIST(10020),

    /**
     * 账户不存在
     */
    EX_ACCOUNT_NOTFOUND(30002),

    /**
     * 账户异常
     */
    EX_ACCOUNT_ABNORMAL(30003),

    /**
     * 访问被拒绝
     */
    EX_HTTP_ACCESS_DENIED(90005),

    /**
     * 系统异常，请联系客服
     */
    EX_SYSTEM(99999);

    private int code;

    ExceptionCodeEnum(int code) {
        this.code = code;
    }

    public int code() {
        return this.code;
    }
}