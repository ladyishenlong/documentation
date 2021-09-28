#centos7安装docker

- 安装docker
```shell
yum update -y
yum install -y yum-utils
yum-config-manager \
--add-repo \
http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
yum makecache fase
yum install docker-ce docker-ce-cli containerd.io -y
```

- 启动docker
```shell
sudo systemctl start docker
docker --version
sudo systemctl enable docker
```
- 修改配置
```shell
mkdir /data/docker
vi /etc/docker/daemon.json
```

```shell
{
"registry-mirrors": [
"https://registry.docker-cn.com"
],
"data-root": "/data/docker/",
"log-driver": "json-file",
"log-opts": {
"max-size": "1000m",
"max-file": "5"
}
}
```

- 配置生效
```shell

systemctl daemon-reload
systemctl restart docker
docker info
```


```json
{
  "registry-mirrors": [
    "https://registry.docker-cn.com"
  ],
  "insecure-registries": [
    "http://{内部ip}"
  ],
  "data-root": "/home/docker/",
  "log-driver": "json-file",
  "log-opts": {
    "max-size": "1000m",
    "max-file": "5"
  }
}
```

