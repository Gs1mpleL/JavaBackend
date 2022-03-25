package 反射;

import domain.Person;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * 获取类信息
 * @author wanfeng
 * @create 2022/3/25 11:32
 * @package 反射
 */
public class Dome1 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 类信息获取
        Class<Person> personClass = (Class<Person>) Class.forName("domain.Person");
        System.out.println(personClass); // class domain.Person
        System.out.println(personClass.getSimpleName()); // Person
        System.out.println(personClass.getName()); // domain.Person
        System.out.println(personClass.newInstance()); // Person(name=null, age=null, date=null)


        // 获取所有构造器构造器
        Constructor<?>[] constructors = personClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        // 获取单个构造器带所有参数的
        Constructor<Person> constructor = personClass.getConstructor(String.class, Integer.class, Date.class,String.class);
        Person aaa = constructor.newInstance("aaa", 12, new Date(),"这是一个公开字段");
        System.out.println(aaa);

        // 成员public变量
        Field open = personClass.getField("open");
        // 获取后可以获取或者修改对象的值
        System.out.println(open.get(aaa));
        open.set(aaa,"这是修改后的字段");
        System.out.println(open.get(aaa));

        // 成员私有变量
        // Field name = personClass.getField("name");   直接找私有的找不见
        Field name = personClass.getDeclaredField("name");
        name.setAccessible(true);   // 不修改权限，name.xx会报错误
        name.set(aaa,"修改了aaa的name");
        System.out.println(name.get(aaa));


        // 获得方法
        Method[] method = personClass.getMethods();
        Method isAMethod = personClass.getMethod("isAMethod");
        System.out.println(isAMethod.getName());
        System.out.println(isAMethod.invoke(aaa));

        // 私有方法
        Method isAPrivateMethod = personClass.getDeclaredMethod("isAPrivateMethod");
        System.out.println(isAPrivateMethod.isAnnotationPresent(Bean.class));
        isAPrivateMethod.setAccessible(true);
        isAPrivateMethod.invoke(aaa);




        // 带参数方法
        Method test = personClass.getMethod("test", String.class);
        test.invoke(aaa,"AAA");



        // 注解
        System.out.println(Arrays.toString(personClass.getDeclaredAnnotations()));
    }
}
