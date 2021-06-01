## create keystore
```sh
keytool -genkeypair -alias mytestkey -keyalg RSA -dname "CN=Web Server,OU=Unit,O=Organization,L=City,S=State,C=US" -keypass changeme -keystore server.jks -storepass changeme
keytool -importkeystore -srckeystore server.jks -destkeystore server.jks -deststoretype pkcs12
keytool -list -v -keystore server.jks
```

## add keystore config for Server Site Decrypt
```yaml
encrypt:
  keyStore:
    location: 'classpath:/keystore/server.jks'
    alias: mytestkey
    password: changeme
    secret: changeme
```

## build and publish image
```sh
spring-boot:build-image -DDOCKER_USERNAME=username \
-DDOCKER_PASSWORD=password \
-DDOCKER_URL=url \
-DDOCKER_EMAIL=email \
-DDOCKER_WORKSPACE=workspace -f pom.xml
```

## run in docker
```sh
docker run -p 8888:8888 \
-e "SPRING_CLOUD_CONFIG_SERVER_GIT_URI=https://github.com/xxx/xxx.git" \
-e "SPRING_CLOUD_CONFIG_SERVER_GIT_USERNAME=username" \
-e "SPRING_CLOUD_CONFIG_SERVER_GIT_PASSWORD=password" \
--name arch-config-server \
-d url/workspace/project.artifactId:latest
```

## run in k8s

```
kubectl config set-context docker-desktop --namespace=kioyong
kubectl apply -f k8s/configMap.yaml
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml


```