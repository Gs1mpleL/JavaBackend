package Redis;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author wanfeng
 * @create 2022/3/25 16:21
 * @package Redis
 */
public class 全局唯一ID {

    private final static long BEGIN_TIMESTAMP = 1640995200L;



    public static void main(String[] args) {
        LocalDateTime time = LocalDateTime.of(2022,1,1,0,0,0);
        long l = time.toEpochSecond(ZoneOffset.UTC);
        System.out.println(l);
    }
}
