# drone部署

## 下载镜像
```shell
docker pull drone/drone:2.0.2
```

## 生成共享密钥
- 使用openssl生成密钥
```shell
openssl rand -hex 16
```

## 运行drone
```shell
docker run -td --name=drone \
-p 9080:80 \
-p 9443:443 \
--restart=always \
-v /data/dockerMount/drone:/data \
--env=DRONE_AGENTS_ENABLED=true \
--env=DRONE_SERVER_PROTO=http \
--env=DRONE_SERVER_HOST= {drone的域名或者ip}\
--env=DRONE_GITLAB_SKIP_VERIFY:true \
--env=DRONE_GITLAB_SERVER={gtilab的域名或者ip} \
--env=DRONE_GITLAB_CLIENT_ID={gitlab客户端id} \
--env=DRONE_GITLAB_CLIENT_SECRET={gitlab密钥} \
--env=DRONE_RPC_SECRET={共享密钥} \
drone/drone:2.0.2
```

## 运行runner
- 在部署了drone之后还需要runner来运行任务

### 部署ssh-runner
```shell
docker run -d \
  -p 9300:3000 \
  --restart always \
  --name ssh-runner \
  -e DRONE_RUNNER_CAPACITY=1 \
  -e DRONE_DEBUG=true \
  -e DRONE_RPC_PROTO=http \
  -e DRONE_RPC_HOST={drone的域名} \
  -e DRONE_RPC_SECRET={共享密钥} \
  drone/drone-runner-ssh
```



### 部署docker-runner
- 挂载是为了获得docker的进程
```shell
docker run -d \
  --restart always \
  --name docker-runner \
  -p 9301:3000 \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -e DRONE_RPC_PROTO=http \
  -e DRONE_RPC_HOST={drone的域名}\
  -e DRONE_RPC_SECRET={共享密钥} \
  -e DRONE_RUNNER_CAPACITY=2 \
  -e DRONE_RUNNER_NAME=docker-runner \
  drone/drone-runner-docker
```