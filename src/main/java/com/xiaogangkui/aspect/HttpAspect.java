package com.xiaogangkui.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Created by luchunyu
 */
@Component
@Aspect
public class HttpAspect {

//    @Pointcut("execution(* com.gaogao.platform.util.common.HttpUtil.*(..))")
//    public void perform() {
//
//    }
//
//    @Around("perform()")
//    public Object aroundPerform(ProceedingJoinPoint joinPoint) {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        System.out.println("方法名：" + signature.getMethod().getName());
//        try {
//            return joinPoint.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        return null;
//
//    }
}
