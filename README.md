全网最简单的SpringCloud整合Seata仓库

切换 master-success 分支 
1. db文件夹下面有四个SQL文件，直接运行即可
2. 按照顺序依次启动 seata-eurake、seata-order、seata-account、seata-storage
3. 在 seata-server 模块下面找到 seata-server.bat 运行
4. 通过 postman 进行测试请求 
```
# 模拟全局事务会滚
127.0.0.1:8001/order/create?userId=1&productId=1&count=2&money=200
```
```
# 模拟全局事务提交
127.0.0.1:8001/order/create?userId=1&productId=1&count=2&money=20
```
