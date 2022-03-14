package 代理模式;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *
 * @author wanfeng
 * @create 2022/3/14 10:55
 * @package 代理模式
 */
public class UserServiceInvocation implements InvocationHandler {
    // 需要代理的对象
    private final UserService userService;

    public UserServiceInvocation(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置操作");
        Object invoke = method.invoke(userService);
        System.out.println("后置操作");
        return invoke;
    }
}
