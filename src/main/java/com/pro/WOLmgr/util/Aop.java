package com.pro.WOLmgr.util;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Log4j2
public class Aop {

    // service 패키지
    @Pointcut("execution(* com.pro.WOLmgr.service.*.*(..))")
    private void pointCut(){}

    // @ExeTimer 어노테이션이 달렸을 때
    @Pointcut("@annotation(com.pro.WOLmgr.util.ExeTimer)")
    private void timer(){}

    // 시간을 측정할 수 있음.
    @Around("timer()")
    public void AssumeExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        joinPoint.proceed(); // 조인포인트의 메서드 실행
        stopWatch.stop();

        long totalTimeMillis = stopWatch.getTotalTimeMillis();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();

        log.info("실행 메서드: {}, 실행시간 = {}ms", methodName, totalTimeMillis);
    }

    // 클래스 위치와 메서드 위치를 띄워줌
    @Before("pointCut()")
    public void logMethodExecution(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        log.info("[{}] 클래스의 {} 메서드 실행 시작됨", className, methodName);
    }
}
