package com.icore.winvaz.winvazcommon.exception;

/**
 * @Deciption 顶级异常，所有的自定义异常继承此类
 * @Author wdq
 * @Create 2020/8/11 15:16
 * @Version 1.0.0
 */
public class WinVazCloudException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WinVazCloudException(String message, Throwable cause) {
        super(message, cause);
    }

    public WinVazCloudException(String message) {
        super(message);
    }

    public WinVazCloudException(Throwable cause) {
        super(cause);
    }

    public WinVazCloudException() {
        super();
    }

    public WinVazCloudException(
            String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
    }
}
