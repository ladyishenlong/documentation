#k8s部署

##环境
- 三台服务器
```shell
ip1
ip2
ip3
```
- 修改主机名
```shell
hostnamectl set-hostname k8s-master-1
hostnamectl set-hostname k8s-work-1
hostnamectl set-hostname k8s-work-2
```
##准备
- 挂载好数据盘，路径是/data


##安装 docker kubelet
- docker默认最新版本
- kubelet版本为1.21.2
```shell
sh install_kubelet.sh
```


