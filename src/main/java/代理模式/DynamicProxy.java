package 代理模式;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理模式
 * @author wanfeng
 * @create 2022/3/14 10:58
 * @package 代理模式
 */
public class DynamicProxy {
    private final Object target;
    private final InvocationHandler handler;

    public DynamicProxy(Object target, InvocationHandler handler) {
        this.target = target;
        this.handler = handler;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
    }

    public static void main(String[] args) {
        DynamicProxy dynamicProxy=  new DynamicProxy(new UserServiceImpl(),new UserServiceInvocation(new UserServiceImpl()));
        UserService proxy = (UserService) dynamicProxy.getProxy();
        proxy.update();
    }


}

