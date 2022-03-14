package Spring.Bean的理解;

import org.springframework.stereotype.Repository;

/**
 * @author wanfeng
 * @created 2022/3/14 14:32
 * @package Spring
 */
@Repository
public class UserDaoImpl2 implements UserDao{
    @Override
    public void update() {
        System.out.println("另一个实现");
    }
}
