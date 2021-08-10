#harbor部署
- 先安装docker以及docker-compose

- 下载离线包，建议获取最新版本
> https://github.com/goharbor/harbor/releases

- 解压文件
```shell
tar -xf harbor-offline-installer-vx.x.x.tgz
```

- 解压出harbor文件，配置其内部的harbor.yml 内部有模板，修改需要的即可
```shell
external_url: {外部域名}
#初始密码
harbor_admin_password: Harbor12345
# Harbor DB configuration
database:
password: {数据库密码}
max_idle_conns: 50
max_open_conns: 1000

data_volume: {数据位置}
```

- 部署harbor
```shell
sh install.sh
```

- 修改配置文件,修改harbor.yml后执行
```shell
./prepare
docker-compose down -v
docker-compose up -d
```

