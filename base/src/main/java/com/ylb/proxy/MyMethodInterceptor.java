package com.ylb.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib原理：
 * 代理类去继承目标类，每次调用代理类的方法都会被方法拦截器拦截，
 * 在拦截器中才是调用目标类的该方法的逻辑
 * AOP的原理 是使用JDK动态代理和cglib动态代理技术来实现的
 * JDK实现动态代理需要实现类通过接口定义业务方法，对于没有接口的类，如何实现动态代理呢，这就需要CGLib了
 *
 * 2. cglib的动态代理
 *
 * CGLib采用了非常底层的字节码技术，其原理是通过字节码技术为一个类创建子类，并在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。JDK动态代理与CGLib动态代理均是实现Spring AOP的基础。
 */
public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("这里是对目标类进行增强！！！");
        //注意这里的方法调用，不是用反射哦！！！
        Object object = methodProxy.invokeSuper(o, objects);
        return object;
    }
}
