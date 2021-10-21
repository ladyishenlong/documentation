# 修改yum源

```shell
cd /etc/yum.repos.d/
#CentOS7 
wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
yum makecache
```