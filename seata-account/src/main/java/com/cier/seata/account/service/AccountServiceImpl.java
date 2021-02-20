package com.cier.seata.account.service;

import com.cier.seata.account.dao.AccountDao;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    private final BigDecimal ERROR_MONEY = new BigDecimal("200");
    private final AccountDao accountDao;
    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * 扣减账户余额
     *
     * @param userId 用户id
     * @param money  金额
     */
    @Override
    @Transactional
    public void decrease(Long userId, BigDecimal money) {
        log.info("[decrease] ------->扣减账户开始account中");
        log.info("[decrease] xid : {}", RootContext.getXID());
        //模拟全局事务回滚 条件：money=200
        if (ERROR_MONEY.compareTo(money) == 0) {
            throw new RuntimeException("非法参数,money为：" + money);
        }
        accountDao.decrease(userId, money);
        log.info("[decrease]------->扣减账户结束account中");
    }
}
