package Spring.Bean的理解;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import 代理模式.案例.UserService;
import 代理模式.案例.UserServiceImpl;

import javax.annotation.Resource;

/**
 * @author wanfeng
 * @created 2022/3/14 13:54
 * @package Spring
 */
@Configuration
@Service
public class BeanDome {
    /**
     * @Bean 注册一个bean
     * @return Bean的class
     */
    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }



    public UserDao userDao;
    @Autowired
    @Qualifier("userDaoImpl2")
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Resource(type = UserDaoImpl2.class)
    public UserDao userDao1;

    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("通过@Configuration加在类上和 @Bean 加载方法上注解来注册Bean->"+app.getBean("userService"));
        System.out.println("通过@Component加在类上来注册Bean->"+app.getBean("userDaoImpl"));

        BeanDome service = (BeanDome)app.getBean("beanDome");
        // 注入接口会自动寻找他的实现类
        service.userDao.update();
        service.userDao1.update();
    }
}
