package com.ylb.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 静态编译和动态编译
 * 静态编译： 在编译时确定类型，绑定对象
 * 动态编译： 运行时确定类型，绑定对象
 * 反射机制优缺点
 * 优点： 运行期类型的判断，动态加载类，提高代码灵活度。
 * 缺点： 1,性能瓶颈：反射相当于一系列解释操作，通知 JVM 要做的事情，性能比直接的 java 代码要慢很多。2,安全问题，让我们可以动态操作改变类的属性同时也增加了类的安全隐患。
 * 反射的应用场景
 * 反射是框架设计的灵魂。
 *
 * 在我们平时的项目开发过程中，基本上很少会直接使用到反射机制，但这不能说明反射机制没有用，实际上有很多设计、开发都与反射机制有关，例如模块化的开发，通过反射去调用对应的字节码；动态代理设计模式也采用了反射机制，还有我们日常使用的 Spring／Hibernate 等框架也大量使用到了反射机制。
 *
 * 举例：
 *
 * 我们在使用 JDBC 连接数据库时使用 Class.forName()通过反射加载数据库的驱动程序；
 * Spring 框架的 IOC（动态加载管理 Bean）创建对象以及 AOP（动态代理）功能都和反射有联系；
 * 动态配置实例的属性；
 */
public class Reflection {
    public static void main(String[] args) throws ClassNotFoundException {
        /* 1.知道具体类的情况下可以使用： */
        Class alunbarClass = TargetObject.class;

        /*
        但是我们一般是不知道具体类的，基本都是通过遍历包下面的类来获取 Class 对象
        2.通过 Class.forName()传入类的路径获取：
        */

        Class alunbarClass1 = Class.forName("cn.javaguide.TargetObject");
    }

    public void testReflect() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchFieldException{
        /**
         * 获取TargetObject类的Class对象并且创建TargetObject类实例
         */
        Class<?> tagetClass = Class.forName("cn.javaguide.TargetObject");
        TargetObject targetObject = (TargetObject) tagetClass.newInstance();
        /**
         * 获取所有类中所有定义的方法
         */
        Method[] methods = tagetClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        /**
         * 获取指定方法并调用
         */
        Method publicMethod = tagetClass.getDeclaredMethod("publicMethod",
                String.class);

        /**
         * 参数（对象，参数）
         */
        publicMethod.invoke(targetObject, "JavaGuide");
        /**
         * 获取指定参数并对参数进行修改
         */
        Field field = tagetClass.getDeclaredField("value");
        //为了对类中的参数进行修改我们取消安全检查
        field.setAccessible(true);
        field.set(targetObject, "JavaGuide");
        /**
         * 调用 private 方法
         */
        Method privateMethod = tagetClass.getDeclaredMethod("privateMethod");
        //为了调用private方法我们取消安全检查
        privateMethod.setAccessible(true);
        privateMethod.invoke(targetObject);
    }
}
