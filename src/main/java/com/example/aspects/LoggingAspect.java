package com.example.aspects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspect for logging execution of controller, service, and repository Spring components.
 * This aspect is configured to log method entry and exit for components annotated with
 * {@code @RestController}, {@code @Service}, and {@code @Repository}.
 *
 * @author Klezovich Ivan
 * @since 2023-10-04
 */
@Aspect
@Component
public class LoggingAspect {

    /**
     * Logger instance for this class.
     */
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Pointcut that matches all Spring beans annotated with {@code @RestController}.
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {}

    /**
     * Pointcut that matches all Spring beans annotated with {@code @Service}.
     */
    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void service() {}

    /**
     * Pointcut that matches all Spring beans annotated with {@code @Repository}.
     */
    @Pointcut("@annotation(org.springframework.stereotype.Repository)")
    public void repository() {}

    /**
     * Advice that logs methods covering the {@code controller} pointcut.
     *
     * @param joinPoint join point for advice
     * @return result of the method execution
     * @throws Throwable if the method execution throws an exception
     */
    @Around("controller()")
    public Object logAroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        return logAround(joinPoint, "Controller");
    }

    /**
     * Advice that logs methods covering the {@code service} pointcut.
     *
     * @param joinPoint join point for advice
     * @return result of the method execution
     * @throws Throwable if the method execution throws an exception
     */
    @Around("service()")
    public Object logAroundService(ProceedingJoinPoint joinPoint) throws Throwable {
        return logAround(joinPoint, "Service");
    }

    /**
     * Advice that logs methods covering the {@code repository} pointcut.
     *
     * @param joinPoint join point for advice
     * @return result of the method execution
     * @throws Throwable if the method execution throws an exception
     */
    @Around("repository()")
    public Object logAroundRepository(ProceedingJoinPoint joinPoint) throws Throwable {
        return logAround(joinPoint, "Repository");
    }

    /**
     * Common logging method for all layers. Logs method entry and exit, and handles exceptions.
     *
     * @param joinPoint join point for advice
     * @param layer     the layer (Controller, Service, Repository)
     * @return result of the method execution
     * @throws Throwable if the method execution throws an exception
     */
    private Object logAround(ProceedingJoinPoint joinPoint, String layer) throws Throwable {
        if (log.isInfoEnabled()) {
            log.info("{} - Enter: {}.{}() with argument[s] = {}", layer, joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }

        try {
            Object result = joinPoint.proceed();
            if (log.isInfoEnabled()) {
                log.info("{} - Exit: {}.{}() with result = {}", layer, joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("{} - Illegal argument: {} in {}.{}()", layer, Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }
    }
}
