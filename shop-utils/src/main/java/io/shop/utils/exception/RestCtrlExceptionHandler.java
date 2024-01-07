package io.shop.utils.exception;

import io.shop.utils.constants.HttpCode;
import io.shop.utils.resp.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestCtrlExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(RestCtrlExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        logger.error("服务器抛出了异常：{}", e.getMessage());
        return new Result<>(HttpCode.FAILURE, "执行失败", e.getMessage());
    }
}
