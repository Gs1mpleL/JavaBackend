package com.wanfeng.springboot.配置注解;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义配置类   通过ConfigurationProperties(prefix = "xxxx") 来创建新的配置 在application.yml中就可以使用了
 * @author wanfeng
 * @created 2022/3/8 12:04
 * @package com.wanfeng.springboot.配置注解
 */
@Component
@Data
@ConfigurationProperties(prefix = "test-properties")
public class ConfigureDome {
    private String name;
    private String age;
}
