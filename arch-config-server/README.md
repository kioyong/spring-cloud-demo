# Config Server Demo


 - 服务端解密 ✔️
 - 客户端解密 ✔️
 - KeyStore 配置 ✔️
 - 构建镜像 ✔️
 - 发布镜像 ✔️
 - 部署到k8s ✔️
 - 构建Spring native 镜像❌

## 创建 Keystore

创建自己的keystore 并替换掉 keystore/server.jks

```shell
keytool -genkeypair -alias mytestkey -keyalg RSA -dname "CN=Web Server,OU=Unit,O=Organization,L=City,S=State,C=US" -keypass changeme -keystore server.jks -storepass changeme
keytool -importkeystore -srckeystore server.jks -destkeystore server.jks -deststoretype pkcs12
keytool -list -v -keystore server.jks
```


## 添加keystore配置(用于服务端解密)
```yaml
encrypt:
  keyStore:
    location: 'classpath:/keystore/server.jks'
    alias: 'mytestkey'
    password: 'changeme'
    secret: 'changeme'
```


## 构建和发布镜像

先修改pom.xml 里面的镜像仓库地址等配置
```shell
spring-boot:build-image
```

或者使用环境变量修改配置
```shell
spring-boot:build-image -DDOCKER_USERNAME=user \
-DDOCKER_PASSWORD=secret \
-DDOCKER_URL=https://docker.example.com/v1/ \
-DDOCKER_EMAIL=user@example.com \
-DDOCKER_WORKSPACE=workspace -f pom.xml
```


## 在docker运行
```sh
docker run -p 8888:8888 \
-e "SPRING_CLOUD_CONFIG_SERVER_GIT_URI=https://github.com/xxx/xxx.git" \
-e "SPRING_CLOUD_CONFIG_SERVER_GIT_USERNAME=username" \
-e "SPRING_CLOUD_CONFIG_SERVER_GIT_PASSWORD=password" \
--name arch-config-server \
-d url/workspace/project.artifactId:latest
```


## 在Kubernetes运行

先修改configMap.yaml文件
```shell
  SPRING_CLOUD_CONFIG_SERVER_GIT_URI: "YOUR_GIT_CONFIG_URI"
  SPRING_CLOUD_CONFIG_SERVER_GIT_USERNAME: "YOUR_GIT_USERNAME"
  SPRING_CLOUD_CONFIG_SERVER_GIT_PASSWORD: "YOUR_GIT_PASSWORD"
```

然后运行下面的命令
```shell
kubectl config set-context docker-desktop --namespace=arch-dev
kubectl apply -f k8s/config.yaml
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml

```

