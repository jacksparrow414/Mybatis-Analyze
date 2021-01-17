package com.example.mybatis.demomybatis.service.impl;

import com.example.mybatis.demomybatis.config.CustomDataSourceConfiguration;
import com.example.mybatis.demomybatis.dao.UserMapper;
import com.example.mybatis.demomybatis.entity.UserEntity;
import com.example.mybatis.demomybatis.service.UserService;
import com.example.mybatis.demomybatis.service.UserTransactionService;
import java.util.concurrent.ThreadPoolExecutor;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 测试事务注解时把模仿的自定义的ShardingSphereDatasource配置类{@link CustomDataSourceConfiguration}先注释掉
 *
 * 在异步线程中控制事务可以使用transactionManager和transactionTemplate两种方式
 *
 * @Author jacksparrow414
 * @Date 2019-11-27
 * @Description: UserServiceImpl
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserTransactionService userTransactionService;

    @Resource(name = "transactionManager")
    private PlatformTransactionManager transactionManager;

    @Resource(name = "threadPoolExecutor")
    private ThreadPoolExecutor threadPoolTaskExecutor;
    
    @Resource(name = "transactionTemplate")
    private TransactionTemplate transactionTemplate;
    
    /**
     * 这种方式,最外层的方法加上事务注解，即使下面的里面的嵌套方法不加注解，异常出现，两个事务都可以回滚成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserEntity entity) {
        userMapper.addUser(entity);
        try {
            //addAnotherUser();
            userTransactionService.saveUserCatchException();
        }catch (Exception exception){
            // TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            //log.error("同一个类中子方法执行出错", exception);
            log.error("另一个类中有Transactional注解方法执行出错，但是异常被调用处catch了", exception);
        }
        // userTransactionService.saveUserThrowException();
        
    }

    /**
     * 这种方式,即：当同一个service，暴露的接口方法上没有事务注解，只有同一个serviceImpl子方法有事务注解的情况，
     * 一旦是下面这种方法出错，则两个事务都回滚不了。
     * 因为Spring的事务是依赖AOP的
     * 虽然下面的有注解，但是同一个类不会再去动态代理，所以也就不会再有事务
     */
    @Transactional(rollbackFor = Exception.class)
    public void addAnotherUser() {
        UserEntity entity = UserEntity.builder().id(12).age(12).name("jack").build();
        userMapper.addUser(entity);
        int a = 1/0;
    }


    @Override
    public boolean asyncThreadTransaction() {
        // 手动回滚事务
        threadPoolTaskExecutor.execute(() -> {
            UserEntity wrong = UserEntity.builder().id(113).age(2021).name("异步线程事务").build();
            TransactionDefinition transactionDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus transaction = transactionManager.getTransaction(transactionDefinition);
            try {
                userMapper.addUser(wrong);
                int a = 1/0;
                transactionManager.commit(transaction);
            }catch (Exception e){
                log.error("发生报错，回滚事务", e);
                transactionManager.rollback(transaction);
            }
        });

        // 执行正确，手动提交
        threadPoolTaskExecutor.execute(() -> {
            UserEntity correct = UserEntity.builder().id(114).age(2020).name("正常线程事务").build();
            TransactionDefinition transactionDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus transaction = transactionManager.getTransaction(transactionDefinition);
            try {
                userMapper.addUser(correct);
                transactionManager.commit(transaction);
            }catch (Exception e){
                log.error("发生报错，回滚事务", e);
                transactionManager.rollback(transaction);
            }
        });
        return false;
    }
    
    @Override
    public boolean asyncThreadTransactionTemplate() {
        // transactionTemplate执行错误时自动回滚事务
        threadPoolTaskExecutor.execute(() -> {
            transactionTemplate.execute(transactionStatus -> {
                UserEntity wrong = UserEntity.builder().id(115).name("template异常事务").age(2019).build();
                 userMapper.addUser(wrong);
                 int tmp = 1/0;
                 return true;
            });
        });
        
        // transactionTemplate正常执行
        threadPoolTaskExecutor.execute(() -> {
            transactionTemplate.execute(transactionStatus -> {
                UserEntity correct = UserEntity.builder().id(116).name("template正常执行").age(2018).build();
                userMapper.addUser(correct);
                return true;
            });
        });
        return false;
    }
}
