package 代理模式.CGLIB动态代理;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import 代理模式.案例.UserServiceImpl;

import java.lang.reflect.Method;

/**
 * JDK 动态代理只能代理实现了接口的类或者直接代理接口，而 CGLIB 可以代理未实现任何接口的类。
 * @author wanfeng
 * @created 2022/3/14 13:04
 * @package 代理模式.CGLIB动态代理
 */
public class CglibDome {
    public static void main(String[] args) {
        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(UserServiceImpl.class.getClassLoader());
        // 设置被代理的类
        enhancer.setSuperclass(UserServiceImpl.class);
        // 设置方法拦截器
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("前置增强");
                methodProxy.invokeSuper(o,objects);
                System.out.println("后置增强");
                return null;
            }
        });
        // 创建对象
        UserServiceImpl service = (UserServiceImpl)enhancer.create();
        service.update();
    }
}



class UserServiceInterceptor implements MethodInterceptor{

    /**
     * @param o           代理对象（增强的对象）
     * @param method      被拦截的方法（需要增强的方法）
     * @param objects        方法入参
     * @param methodProxy 用于调用原始方法
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //调用方法之前，我们可以添加自己的操作
        System.out.println("before method " + method.getName());
        Object object = methodProxy.invokeSuper(o, objects);
        //调用方法之后，我们同样可以添加自己的操作
        System.out.println("after method " + method.getName());
        return object;
    }
}