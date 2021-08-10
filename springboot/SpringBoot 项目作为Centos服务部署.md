#SpringBoot 项目作为Centos服务部署

- 首先需要有个打包好的jar包，确保java -jar 能启动便可
- centos上需要有java环境
- 执行命令
```shell
cd /etc/systemd/system
```

- 创建文件，这里的demo-233是我的项目名称
``` shell
vi demo-233.service
```

- 输入内容，其中/usr/bin/java是java绝对路径可以用which java得到，后半部分则是jar包的绝对路径；输入后保存退出即可
``` shell
[Unit] 
Description=demo-233
After=syslog.target network.target 
 
[Service] 
Type=simple 
 
ExecStart=/usr/bin/java -jar /spring-project/demo-233.jar 
ExecStop=/bin/kill -15 $MAINPID 
 
User=root 
Group=root 
 
[Install] 
WantedBy=multi-user.target
```

## 服务启动相关命令

- 修改文件后刷新

```shell
systemctl daemon-reload
```


- 服务状态
```shell
systemctl status demo-233
```


- 启动服务
```shell
systemctl start demo-233
```


- 停止服务
```shell
systemctl stop demo-233
```

- 服务重启
```shell
systemctl restatus demo-233
```

- 服务开机启动
```shell
systemctl enable demo-233
```

- 服务关闭开机启动
```shell
systemctl disable demo-233
```
