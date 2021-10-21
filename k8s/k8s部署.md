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
hostnamectl set-hostname k8s-m1
hostnamectl set-hostname k8s-w1
hostnamectl set-hostname k8s-w2
```


## 数据盘挂载
- 分区
```shell
fdisk  /dev/vdb
n->p->1->回车->回车->w
```
- 写入文件系统
```shell
mkfs.ext4 /dev/vdb1
```
- 挂载节点
```shell
mkdir /data
mount /dev/vdb1  /data
```
- 查看
```shell
df -h
```

- 开启自动挂载
```shell
vi /etc/fstab
#打开后，在最后一行加入以下代码，如果上面用的是ext3，这里也要用ext3；
/dev/vdb1 /data ext4 defaults 0 1 
```

##安装 docker kubelet
- docker默认最新版本 kubelet版本为1.21.2
```shell
export REGISTRY_MIRROR=https://registry.cn-hangzhou.aliyuncs.com
sh install_kubelet.sh
```

## master初始化
```shell
#master节点初始化
export MASTER_IP={master节点ip地址}
# 替换 apiserver.demo 为 您想要的 dnsName
export APISERVER_NAME=k8s.server
# Kubernetes 容器组所在的网段，该网段安装完成后，由 kubernetes 创建，事先并不存在于您的物理网络中
export POD_SUBNET=10.100.0.1/16
echo "${MASTER_IP}    ${APISERVER_NAME}" >> /etc/hosts
```
```shell
curl -sSL https://kuboard.cn/install-script/v1.21.x/init_master.sh | sh -s 1.21.4 /coredns
```

## 安装网络插件
```shell
export POD_SUBNET=10.100.0.0/16
kubectl apply -f https://kuboard.cn/install-script/v1.21.x/calico-operator.yaml
wget https://kuboard.cn/install-script/v1.21.x/calico-custom-resources.yaml
sed -i "s#192.168.0.0/16#${POD_SUBNET}#" calico-custom-resources.yaml
kubectl apply -f calico-custom-resources.yaml
```

## 初始化 worker节点
```shell
# 只在 master 节点执行
kubeadm token create --print-join-command

# 获得结果
kubeadm join k8s.server:6443 --token 5cg17f.1fpw32aiko5nodye --discovery-token-ca-cert-hash sha256:5416959646a40669d3d64e68b5dc0cfc13b468915b7da5b9c8d4d6c02fc3c07b 

```

```shell
# 只在 worker 节点执行
# 替换 x.x.x.x 为 master 节点的内网 IP
export MASTER_IP={master节点ip地址}
export APISERVER_NAME=k8s.server
echo "${MASTER_IP}    ${APISERVER_NAME}" >> /etc/hosts

# 替换为 master 节点上 kubeadm token create 命令的输出
kubeadm join k8s.server:6443 --token 5cg17f.1fpw32aiko5nodye \
--discovery-token-ca-cert-hash sha256:5416959646a40669d3d64e68b5dc0cfc13b468915b7da5b9c8d4d6c02fc3c07b 
```

## 安装helm
```shell
version=v3.6.3
#从华为开源镜像站下载
curl -LO https://repo.huaweicloud.com/helm/v3.6.3/helm-v3.6.3-linux-amd64.tar.gz
tar -zxvf helm-v3.6.3-linux-amd64.tar.gz
mv linux-amd64/helm /usr/local/bin/helm && rm -rf linux-amd64
```
## 安装 traefik-stable
```shell

helm repo add traefik https://helm.traefik.io/traefik
helm repo update
helm install traefik traefik/traefik


kubectl port-forward $(kubectl get pods --selector \
"app.kubernetes.io/name=traefik" --output=name) 9000:9000


```