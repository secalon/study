package com.ylb.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk动态代理
 * JDK动态代理具体实现原理：
 *
 * 1、通过实现InvocationHandlet接口创建自己的调用处理器
 *
 * 2、通过为Proxy类指定ClassLoader对象和一组interface来创建动态代理
 *
 * 3、通过反射机制获取动态代理类的构造函数，其唯一参数类型就是调用处理器接口类型
 *
 * 4、通过构造函数创建动态代理类实例，构造时调用处理器对象作为参数参入
 *
 * JDK动态代理是面向接口的代理模式，如果被代理目标没有接口那么Spring也无能为力，
 *
 * Spring通过java的反射机制生产被代理接口的新的匿名实现类，重写了其中AOP的增强方法。
 *
 * 1.JDK动态代理：通过实现InvocationHandlet接口，并重写里面的invoke方法，
 * 通过为proxy类指定classLoader和一组interfaces来创建动态代理
 */
public class SubjectJDKProxy implements InvocationHandler {

    private Subject subject;

    public SubjectJDKProxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("--------------begin-------------");
        Object invoke = method.invoke(subject, args);
        System.out.println("--------------end-------------");
        return invoke;
    }
}
