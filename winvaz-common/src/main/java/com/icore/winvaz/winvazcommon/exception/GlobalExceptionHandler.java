package com.icore.winvaz.winvazcommon.exception;

import com.icore.winvaz.winvazcommon.web.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;

/**
 * @Deciption 全局异常处理
 * @Author wdq
 * @Create 2020/8/7 13:59
 * @Version 1.0.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CodeException.class)
    public Result handle(CodeException e) {
        return Result.failure(e.code().code(), e.getMessage());
    }

    /**
     * 控制层请求参数校验异常
     *
     * @param request
     * @param e
     * @throws
     * @author wdq
     * @create 2021/6/3 17:19
     * @Return com.icore.winvaz.winvazcommon.web.Result
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class, ConstraintViolationException.class})
    public Result controllerExceptionHandler(HttpServletRequest request, Exception e) {
        log.info("控制层的请求参数校验失败，请求路径：{}，请求信息：{}", request.getRequestURI(), e.getMessage());

        // 错误信息
        String errorMessage = null;

        // 方法参数校验
        if (e instanceof MethodArgumentNotValidException) {
            Iterator<FieldError> iterator =
                    ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors().iterator();
            if (iterator.hasNext()) {
                FieldError fieldError = iterator.next();
                errorMessage = String.join("", fieldError.getField(), ":", fieldError.getDefaultMessage());
            }
            if (errorMessage == null) {
                Iterator<ObjectError> objectErrorIterator =
                        ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().iterator();
                if (objectErrorIterator.hasNext()) {
                    ObjectError objectError = objectErrorIterator.next();
                    errorMessage = String.join("", objectError.getDefaultMessage());
                }
            }
        } else if (e instanceof BindException) {
            Iterator<FieldError> iterator = ((BindException) e).getBindingResult().getFieldErrors().iterator();
            if (iterator.hasNext()) {
                FieldError fieldError = iterator.next();
                errorMessage = String.join("", fieldError.getField(), ":", fieldError.getDefaultMessage());
            }
        } else if (e instanceof ConstraintViolationException) {
            Iterator<ConstraintViolation<?>> iterator =
                    ((ConstraintViolationException) e).getConstraintViolations().iterator();
            if (iterator.hasNext()) {
                ConstraintViolation<?> violation = iterator.next();
                errorMessage = String.join("", violation.getInvalidValue().toString(), ":", violation.getMessage());
            }
        }

        return Result.failure(ExceptionCodeEnum.EX_INVALID_PARAMETER.code(), errorMessage);
    }

    /**
     * Service层异常
     * @author wdq
     * @create 2021/6/3 17:44
     * @param request
     * @param e
     * @Return com.icore.winvaz.winvazcommon.web.Result
     * @exception
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CodeException.class)
    public Result serviceExceptionHandler(HttpServletRequest request, CodeException e) {
        Throwable cause = e.getCause();
        if (cause == null) {
            // 打印异常的基本信息
            log.info("业务层的Service异常。请求路径：{}，请求信息：{}", request.getRequestURI(), e.getMessage());
        } else {
            // 打印异常的堆栈信息
            log.info("业务层的Service异常。请求路径：{}，请求信息：{}", request.getRequestURI(), e.getMessage(), e);
        }

        return Result.failure(e.code().code(), e.getMessage());
    }
}