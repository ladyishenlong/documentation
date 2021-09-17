# nacos部署

## 下载镜像
```shell
docker pull nacos/nacos-server:v2.0.3
```

## 单点部署
```shell
docker run -tid --name nacos \
-p 8848:8848 \
-e NACOS_SERVER_IP={本机ip地址} \
--privileged=true \
--restart=always \
-e MODE=standalone \
-v /home/dockerMount/nacos/logs:/home/nacos/logs \
-v /home/dockerMount/nacos/data:/home/nacos/data \
nacos/nacos-server:2.0.3
```


## 集群部署
集群部署必须使用外部数据源，环境写在.env中
```shell
docker run -tid \
--name nacos \
--network=host \
--privileged=true \
--restart=always \
-v /data/dockerMount/nacos/logs:/home/nacos/logs \
-v /data/dockerMount/nacos/data:/home/nacos/data \
--env-file /data/dockerMount/nacos/conf/nacos.env \
nacos/nacos-server:v2.0.3
```