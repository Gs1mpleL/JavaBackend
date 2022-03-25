package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.Date;

/**
 * @author wanfeng
 * @create 2022/3/25 11:32
 * @package domain
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Person {
    private String  name;
    private Integer age;
    private Date date;
    public String open;

    public void isAMethod(){
        System.out.println("公开方法");
    }

    @Bean
    private void isAPrivateMethod(){
        System.out.println("私有方法");
    }

    public void test(String a){
        System.out.println(a);
    }
}
