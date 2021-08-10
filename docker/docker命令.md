#docker命令

## 清理镜像容器
```shell
docker container prune
docker image prune
docker system prune
```

## 清理日志
```shell
for name in $(docker ps -a | awk '{print $1}' | grep -v CONTAINER); do docker inspect $name | grep LogPath | awk '{print $NF}' | tr -d '",' |xargs du -sh;done
echo > /var/lib/docker/containers/adcc540afbfc3a51053687918ef3fb4e70694ab0fce2d266e24634f9f435d74c/adcc540afbfc3a51053687918ef3fb4e70694ab0fce2d266e24634f9f435d74c-json.log
```

##docker-compose

- 安装
```markdown
curl -L https://github.com/docker/compose/releases/download/1.29.2/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
docker-compose --version
```
- 启动
```shell
docker-compose up -d
```


##更新启动命令
```shell
docker container update --restart=always 容器名字
```

##进入容器
```shell
docker exec -it {容器名} /bin/bash

```
