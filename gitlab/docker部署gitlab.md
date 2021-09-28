#docker部署gitlab

##下载镜像
```shell
docker pull gitlab/gitlab-ce:14.0.0-ce.0
```

##运行容器
```shell
docker run \
 --name gitlab \
 --restart always \
 --privileged=true \
 -itd  \
 -p 8080:80 \
 -p 8443:443 \
 -p 2222:22 \
 -p 5000:5000 \
 -p 5005:5005 \
 -v /home/dockerMount/gitlab/conf:/etc/gitlab  \
 -v /home/dockerMount/gitlab/logs:/var/log/gitlab \
 -v /home/dockerMount/gitlab/data:/var/opt/gitlab \
 gitlab/gitlab-ce:14.0.0-ce.0
```

## 修改root用户密码
```shell
docker exec -it gitlab /bin/bash
gitlab-rails console -e production
user = User.where(id: 1).first
user.password = '密码'
user.password_confirmation = '密码'
user.save!
```

## 修改配置
- 修改gitlab.rb文件配置
主要是external_url这一项，这里可以写http使得gitlab能够进行外网访问。https的配置则是可以在gitlab启动之后，在页面上配置

- 配置生效
```shell
docker exec -it gitlab /bin/bash
gitlab-ctl reconfigure
gitlab-ctl restart
```

##备份
```shell
docker exec -it gitlab /bin/bash
docker exec gitlab gitlab-rake gitlab:backup:create
```


##gitlab
gitlab-secrets.json
gitlab.rb
两个文件必须备份转移

```shell
gitlab-rails db
-- Clear project tokens
UPDATE projects SET runners_token = null, runners_token_encrypted = null
-- Clear group tokens
UPDATE namespaces SET runners_token = null, runners_token_encrypted = null;
-- Clear instance tokens
UPDATE application_settings SET runners_registration_token_encrypted = null;
```



