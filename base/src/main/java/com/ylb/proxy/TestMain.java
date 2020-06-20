package com.ylb.proxy;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestMain {
    public static void main(String[] args) {
        /**
         * cglib动态代理
         */
        //在指定目录下生成动态代理类，我们可以反编译看一下里面到底是一些什么东西
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\java\\java_workapace");
        //创建Enhancer对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
        Enhancer enhancer = new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(Dog.class);//继承被代理类
        //设置回调函数
        enhancer.setCallback(new MyMethodInterceptor());
        //这里的creat方法就是正式创建代理类
        Dog proxyDog = (Dog) enhancer.create();
        //调用代理类的eat方法
        proxyDog.eat();

        /**
        * jdk动态代理
         * JDK动态代理：通过实现InvocationHandlet接口，并重写里面的invoke方法，
         * 通过为proxy类指定classLoader和一组interfaces来创建动态代理
        */
        Subject subject = new SubjectImpl();
        InvocationHandler subjectProxy = new SubjectJDKProxy(subject);
        Subject proxyInstance = (Subject) Proxy.newProxyInstance(subjectProxy.getClass().getClassLoader(), subject.getClass().getInterfaces(), subjectProxy);
        proxyInstance.hello("world");
    }
}
