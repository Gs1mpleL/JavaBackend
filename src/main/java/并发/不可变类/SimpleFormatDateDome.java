package 并发.不可变类;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多个线程同时使用格式化会报错   直接使用不可变类
 * @author wanfeng
 * @created 2022/3/18 15:24
 * @package 并发.不可变类
 */
public class SimpleFormatDateDome {
    public static void main(String[] args) throws ParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                TemporalAccessor parse = dateTimeFormatter.parse("1999-10-23");
                System.out.println(parse);
                String a = "??";
            }).start();
        }
    }
}
