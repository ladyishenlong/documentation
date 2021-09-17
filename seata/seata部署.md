# seata部署

## 下载镜像
```shell
docker pull seataio/seata-server:1.4.2
```

## 运行容器
```shell
docker run --name seata-server \
- tid \
-p 8091:8091 \
--restart=always \
--privileged=true \
-v /data/dockerMount/seata/conf/:/root/seata-config  \
--env-file /data/dockerMount/seata/conf/seata.env \
seataio/seata-server:1.4.2
```

##特别注意
registry.conf 密码不能有特殊字符，否则无法注册成功

## 配置
group都是SEATA_GROUP
在配置中心配置 seata.properties 内容为config.txt中的内容
配置service.vgroupMapping.SEATA_GROUP = default