
一.项目初步选型:
SpringBoot   2.3.4.RELEASE
Spring cloud Alibaba   (nacos,config, openFeign,Sentinel,gateway, seata sykwalking)
消息队列：MQ  RocketMq kafka
非关系型数据库：Redis
关系型数据库：Mybatis  MySQL
记录日志：ELK 
Docker 部署全部服务都使用后docker容器化部署，方便迁移



二.记录项目搭建的全流程

1.新建项目zzh-cloud-all 设置项目依赖版本和项目架构

zzh-cloud-all
    zzh-cloud-gateway  网关服务
    zzh-cloud-order    订单服务
    zzh-cloud-goods    商品服务
    zzh-cloud-common   通用基础