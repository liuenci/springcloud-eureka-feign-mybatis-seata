package com.cier.seata.order.service;

import com.cier.seata.order.dao.AppleDao;
import com.cier.seata.order.dao.BananaDao;
import com.cier.seata.order.dao.OrderDao;
import com.cier.seata.order.entity.Order;
import com.cier.seata.order.feign.AccountApi;
import com.cier.seata.order.feign.StorageApi;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final StorageApi storageApi;
    private final AccountApi accountApi;
    private final BananaDao bananaDao;
    private final AppleDao appleDao;

    public OrderServiceImpl(OrderDao orderDao,
                            StorageApi storageApi,
                            AccountApi accountApi,
                            BananaDao bananaDao,
                            AppleDao appleDao) {
        this.orderDao = orderDao;
        this.storageApi = storageApi;
        this.accountApi = accountApi;
        this.bananaDao = bananaDao;
        this.appleDao = appleDao;
    }

    /**
     * 创建订单
     *
     * @param order
     * @return 测试结果：
     * 1.添加本地事务：仅仅扣减库存
     * 2.不添加本地事务：创建订单，扣减库存
     */
    @Override
    @GlobalTransactional(name = "fsp_create_order", timeoutMills = 60000)
    public void create(Order order) {
        log.info("[create] ------->交易开始");
        log.info("[create] xid:{}", RootContext.getXID());
        // 本地方法
        orderDao.create(order);

        // 远程方法 扣减库存
        storageApi.decrease(order.getProductId(), order.getCount());

        // 远程方法 扣减账户余额
        accountApi.decrease(order.getUserId(), order.getMoney());

        log.info("[create] ------->交易结束");
    }


    /**
     * 此方法用于测试本地事务
     *
     * @param count
     */
    @Override
    @Transactional
    public void changeCount(Integer count) {
        bananaDao.updateCount(count);
        if (count == 200) {
            throw new NullPointerException("测试事务异常");
        }
        appleDao.updateCount(count);
    }

    /**
     * 修改订单状态
     */
    @Override
    public void update(Long userId, BigDecimal money, Integer status) {
        log.info("修改订单状态，入参为：userId={},money={},status={}", userId, money, status);
        orderDao.update(userId, money, status);
    }
}
