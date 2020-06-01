### docker 修改容器镜像
```
docker run -t -i training/sinatra /bin/bash
sudo docker commit -m "Added json gem" -a "Docker Newbee" 0b2616b0e5a8 ouruser/sinatra:v2
docker tag 500b941e6f79 tomcat7:jre7
dokcer rename 容器ID mytomcat7:jre7
```

### 拷贝文件
* 从容器中拷贝文件到宿主机
```
docker cp 容器名：容器中要拷贝的文件名及其路径 要拷贝到宿主机里面对应的路径
```
* 从宿主机拷贝文件到容器
```
docker cp 宿主机中要拷贝的文件名及其路径 容器名：要拷贝到容器里面对应的路径
```
### 重启容器
```
docker restart 59ec
```

### 保存镜像
```
docker save spring-boot-docker  -o  /home/wzh/docker/spring-boot-docker.tar
```

### 加载镜像
```
docker load -i ./core-1.0.4-debug.tar
```