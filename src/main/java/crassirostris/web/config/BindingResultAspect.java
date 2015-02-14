package crassirostris.web.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * Created by crassirostris on 15. 2. 7..
 */
@Aspect
public class BindingResultAspect {
    @Pointcut("execution(public String *(.., org.springframework.validation.BindingResult))")
    public void targetBindingResultPointcut() {
    }

    @Around("targetBindingResultPointcut()")
    public Object beforeBindingResultPointcut(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult result = (BindingResult) arg;
                if (result.hasErrors()) {
                    FieldError fieldError = result.getFieldError();
                    throw new IllegalArgumentException(fieldError.getDefaultMessage());
                }
            }
        }
        return joinPoint.proceed();
    }
}

