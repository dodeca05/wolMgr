package com.pro.WOLmgr.util;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Log4j2
public class Aop {

    //프로젝트 전체에 AOP를 띄운건데 향후 Service같은 것들이 추가되면 제한합니다.
    @Pointcut("execution(* com.pro.WOLmgr..*.*(..))")
    private void pointCut(){}

    @Pointcut("@annotation(com.pro.WOLmgr.util.ExeTimer)")
    private void timer(){}

    // 시간을 측정하고 싶은 메서드 위에 @ExTimer 어노테이션을 사용하면 메서드의 실행 시간을 측정 가능합니다.
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
}
