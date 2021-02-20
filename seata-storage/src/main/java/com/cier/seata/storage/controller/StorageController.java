package com.cier.seata.storage.controller;


import com.cier.seata.storage.service.StorageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("storage")
public class StorageController {
    private final StorageService storageService;
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * 扣减库存
     * @param productId 产品id
     * @param count 数量
     * @return
     */
    @RequestMapping("decrease")
    public String decrease(@RequestParam("productId") Long productId,@RequestParam("count") Integer count){
        storageService.decrease(productId,count);
        return "Decrease storage success";
    }

    @RequestMapping("getById")
    public String getById(@RequestParam("productId") Long productId){
        String ss = storageService.getById(productId);
        return ss;
    }
}
