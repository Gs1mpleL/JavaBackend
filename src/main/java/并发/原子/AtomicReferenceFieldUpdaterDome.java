package 并发.原子;

import Spring.Bean的理解.UserDao;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author wanfeng
 * @created 2022/3/18 15:07
 * @package 并发.原子
 */
public class AtomicReferenceFieldUpdaterDome {
    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(UserDao.class,String.class,"name");
}
