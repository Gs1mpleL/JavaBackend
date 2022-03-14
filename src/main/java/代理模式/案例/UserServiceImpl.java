package 代理模式.案例;

import 代理模式.案例.UserService;

/**
 * @author wanfeng
 * @create 2022/3/14 10:46
 * @package 代理模式
 */
public class UserServiceImpl implements UserService {
    @Override
    public void update() {
        System.out.println("Update()方法的实现");
    }
}
