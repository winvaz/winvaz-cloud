package com.icore.winvaz.winvazcommon.exception;

/**
 * @Author wdq
 * @Create 2021/6/4 09:27
 * @Version 1.0.0
 */
public class SystemException extends CodeableException {

    private static final long serialVersionUID = 1L;

    /**
     * 创建系统异常
     *
     * @param cause 原始异常
     */
    public SystemException(Throwable cause) {
        super(ExceptionCodeEnum.EX_SYSTEM, cause);
    }

    /**
     * 创建系统异常
     */
    public SystemException() {
        super(ExceptionCodeEnum.EX_SYSTEM);
    }
}