package com.cier.seata.storage.service;


import com.cier.seata.storage.dao.StorageDao;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class StorageServiceImpl implements StorageService {
    private final StorageDao storageDao;
    public StorageServiceImpl(StorageDao storageDao) {
        this.storageDao = storageDao;
    }

    /**
     * 扣减库存
     *
     * @param productId 产品id
     * @param count     数量
     * @return
     */
    @Override
    @Transactional
    public void decrease(Long productId, Integer count) {
        log.info("[decrease] ------->扣减库存开始");
        log.info("[decrease] xid : {}", RootContext.getXID());
        Integer current = storageDao.getById(productId);
        if (count > current) {
            log.error("[decrease] 扣减库存:{}, 实际库存:{}", count, current);
            throw new RuntimeException("库存不足");
        }
        storageDao.decrease(productId, count);
        log.info("[decrease]------->扣减库存结束");
    }

    @Override
    public String getById(Long productId) {
        Integer used = storageDao.getById(productId);
        return used.toString();
    }
}
