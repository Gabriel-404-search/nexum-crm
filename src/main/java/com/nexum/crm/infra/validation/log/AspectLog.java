package com.nexum.crm.infra.validation.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class AspectLog {
    @Around("@annotation(Log)")
    public Object automaticLog(ProceedingJoinPoint jp) throws Throwable {

        log.info("Inicio: {}", jp.getSignature());

        Object result = jp.proceed();
        log.trace("Resultado: {}",result);

        log.info("Fim: {}", jp.getKind());

        return result;
    }
}
