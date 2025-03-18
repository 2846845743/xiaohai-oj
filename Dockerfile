# 构建镜像
# 基础镜像
FROM openjdk:8-jdk-slim

# 作者
MAINTAINER wenhaihang

# 配置
ENV PARAMS=""

# 时区
ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 添加应用
ADD target/xiaohai-oj-1.0-SNAPSHOT.jar /xiaohai-oj-1.0.jar

ENTRYPOINT ["sh","-c","java -Djava.awt.headless=true  -jar  $JAVA_OPTS /xiaohai-oj-1.0.jar $PARAMS"]