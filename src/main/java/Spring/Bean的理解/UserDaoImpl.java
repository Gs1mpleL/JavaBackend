package Spring.Bean的理解;

import org.springframework.stereotype.Repository;

/**
 * @author wanfeng
 * @created 2022/3/14 14:04
 * @package Spring
 */
@Repository
// @Primary   存在多个实现类是 的默认注入
public class UserDaoImpl implements UserDao{
    @Override
    public void update() {
        System.out.println("run!");
    }
}
