package com.xiaogangkui.service;

import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public interface CglibMethodInterceptor {
    Object intercept(Object sub, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable;
}
