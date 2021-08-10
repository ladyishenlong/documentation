#centos8安装docker


## 升级yum
```shell
yum update -y
```

## 安装docker
```shell
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
sudo yum-config-manager --add-repo   https://download.docker.com/linux/centos/docker-ce.repo
sudo yum install docker-ce docker-ce-cli containerd.io -y
sudo yum install docker-ce docker-ce-cli
sudo systemctl start docker
docker --version
sudo systemctl enable docker
```


## 修改配置
- 限制日志，修改docker路径
```shell
mkdir /data/docker
vi /etc/docker/daemon.json
```
```json
{
  "registry-mirrors": [
    "https://registry.docker-cn.com",
    "{私服https地址}"
  ],
  "insecure-registries": [
    "{私服http地址}"
  ],
  "data-root": "/data/docker/",
  "log-driver": "json-file",
  "log-opts": {
    "max-size": "1000m",
    "max-file": "5"
  }
}
```
```shell
 systemctl daemon-reload
 systemctl restart docker
 docker info
```



##创建docker本地挂载目录
```shell
mkdir /data/dockerMount
```


