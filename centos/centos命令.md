#centos命令

## 设置主机名
```shell
hostnamectl set-hostname {主机名}
```

##查看centos版本
```shell
cat /etc/redhat-release
```

##升级yum
```shell
sudo yum update -y
```


##查看占用端口
``` shell
netstat -lntp
```

##sdkman
- 安装sdkman
```
curl -s "https://get.sdkman.io" | bash
```
- 配置sdkman
```
 source "$HOME/.sdkman/bin/sdkman-init.sh"
```
- 查看版本
```
sdk version
```
- 查看java
```
sdk ls java
```
- 安装java
```
sdk install java 8.0.252-open
```
- 切换默认版本
```
sdk default java 11.0.5.hs-adpt
```



##java
- 查看版本
```
java -version
```
- 检查yum中的java8的包
```
yum list java-1.8*
```
- 安装java8
```
yum install java-1.8.0-openjdk* -y
```
- 后台运行jar包
```
nohup java -jar XXX.jar > /dev/null 2>&1 &
```


##maven
- maven编译打包
```
./mvnw clean install -Dmaven.test.skip=true

mvn clean install -Dmaven.test.skip=true
```

##git
- 查看git 分支
```
git branch 
```
- 更新远程分支
```
git remote update origin --prune
```
- 更新分支代码
```
git pull 
```
- 切换分支
```
git checkout 分支名
```


##docker
- 有dockerFile的的项目构建docker镜像
```
 docker build -t docker-maven-project .
```
- 删除docker镜像
```
docker image rm docker-maven-project
```