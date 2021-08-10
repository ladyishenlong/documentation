#docker部署redis集群

##参考文档
> https://juejin.cn/post/6868814738751488008

##环境说明
- 3台服务器，6个节点；其中6379是主节点
```shell
ip1:6379
ip1:6380
ip2:6379
ip2:6380
ip3:6379
ip3:6380
```

##下载镜像
- docker pull redis:6.2.4

## 创建文件夹
- mkdir /data/dockerMount/redis-{端口号}
- redis下还有conf和data两个文件夹

#配置redis.conf文件
- 放置在conf文件夹下
- 根据ip和端口不同，总共生成六个conf文件
```shell
port {端口号}
requirepass {密码}
masterauth {密码}

protected-mode no
daemonize no
appendonly yes
cluster-enabled yes
cluster-config-file nodes.conf
cluster-node-timeout 15000

cluster-announce-ip {本机ip}
cluster-announce-port {端口号}
cluster-announce-bus-port 1{端口号}
```

#docker部署redis
- 分别在三台服务器执行
```shell
  docker run -tdi \
--restart always \
--name redis-6379 --net host \
-v /data/dockerMount/redis-6379/conf/redis.conf:/usr/local/etc/redis/redis.conf \
-v /data/dockerMount/redis-6379/data:/data \
redis:6.2.4 redis-server /usr/local/etc/redis/redis.conf
```
```shell
docker run -tdi \
--restart always \
--name redis-6380 --net host \
-v /data/dockerMount/redis-6380/conf/redis.conf:/usr/local/etc/redis/redis.conf \
-v /data/dockerMount/redis-6380/data:/data \
redis:6.2.4 redis-server /usr/local/etc/redis/redis.conf
```

# 集群部署
- 进入容器内部
```shell
docker exec -it redis-6379 bash
```

- 执行集群连接
```shell
cd /usr/local/bin/
redis-cli -a {密码} --cluster create ip1:6379 ip1:6380 ip2:6379 ip2:6380 ip3:6379 ip3:6380 --cluster-replicas 1
```