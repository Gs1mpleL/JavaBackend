package 代理模式;

/**
 * 静态代理
 * @author wanfeng
 * @create 2022/3/14 10:47
 * @package 代理模式
 */
public class StaticProxyDome implements UserService{
    // 代理对象内部需要有代理接口的对象
    private final UserService userService;
    // 构造时传入需要的实现类
    public StaticProxyDome(UserService userService) {
        this.userService = userService;
    }
    // 重写方法时进行增强
    @Override
    public void update() {
        System.out.println("前置增强");
        userService.update();
        System.out.println("后置增强");
    }

    public static void main(String[] args) {
        StaticProxyDome staticProxyDome = new StaticProxyDome(new UserServiceImpl());
        staticProxyDome.update();
    }
}
