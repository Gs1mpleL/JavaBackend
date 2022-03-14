package 代理模式.JDK动态代理;

import 代理模式.案例.UserService;
import 代理模式.案例.UserServiceImpl;

import java.lang.reflect.*;

/**
 * JDK动态代理模式
 * @author wanfeng
 * @create 2022/3/14 10:58
 * @package 代理模式
 */
public class DynamicProxy {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 接口本来没有构造器
        // 根据接口的类加载器，接口的类来创建一个带有构造器的class  (一样的类信息，但是有构造器)
        Class<?> proxyClass = Proxy.getProxyClass(UserService.class.getClassLoader(), UserService.class);
        // 获得一个带有InvocationHandler参数的构造器
        Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
        // 通过构造器来获得实例
        UserService userService = (UserService)constructor.newInstance(new InvocationHandler() {
            // method就是调用的方法名称 (参数是实现类和参数) 此处进行前置后置增强
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("前置增强");
                Object invoke = method.invoke(new UserServiceImpl(), args);
                System.out.println("后置增强");
                return invoke;
            }
        });
        userService.update();
        // 快捷方式
        Class[] interfaces = new Class[1];
        interfaces[0] = UserService.class;
        UserService userService1 = (UserService)Proxy.newProxyInstance(UserService.class.getClassLoader(), interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("前置增强");
                Object invoke = method.invoke(new UserServiceImpl(), args);
                System.out.println("后置增强");
                return invoke;
            }
        });
        userService1.update();
    }
}

