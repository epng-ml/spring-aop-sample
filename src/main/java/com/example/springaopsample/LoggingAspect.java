package com.example.springaopsample;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object logGetRequests(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        String[] names = methodSignature.getParameterNames();
        Object[] values = proceedingJoinPoint.getArgs();
        StringBuilder args = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            args.append(String.format("%s=%s", names[i], values[i]));
            if (names.length - 1 > i) args.append("&");
        }

        long startTime = System.nanoTime();
        Object result = proceedingJoinPoint.proceed();
        long stopTime = System.nanoTime();

        logger.info(
                String.format(
                        "Method: %s.%s | Args: %s | Return: %s | Execution Time: %sms",
                        methodSignature.getDeclaringType().getSimpleName(),
                        methodSignature.getName(),
                        args,
                        result,
                        TimeUnit.NANOSECONDS.toMillis(stopTime - startTime)));

        return result;
    }
}
