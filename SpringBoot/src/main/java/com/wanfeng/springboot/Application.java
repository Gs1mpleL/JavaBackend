package com.wanfeng.springboot;

import com.wanfeng.springboot.配置注解.ConfigureDome;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * SpringBoot启动类
 * @author Administrator
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(Application.class, args);
        ConfigureDome configureDome = app.getBean(ConfigureDome.class);
        System.out.println(configureDome);
    }

}
