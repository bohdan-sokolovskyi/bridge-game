package com.bsokolovskyi.bridge.web.async;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    private static final Logger logger = LogManager.getLogger(AsyncExceptionHandler.class);

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        if(logger.isErrorEnabled()) {
            logger.error("{} in method {}({})",
                    ex.getMessage(),
                    method.getName(),
                    Arrays.stream(params).map(Object::toString).collect(Collectors.joining(", ")));
        }
    }
}
