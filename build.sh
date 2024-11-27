
# 普通镜像构建，随系统版本构建 amd/arm
docker build -t wenhaihang/xiaohai-oj-1.0 -f ./Dockerfile .

#启动中间件
docker-compose -f ./dev-ops/docker-compose-environment.yml up -d
docker-compose -f ./dev-ops/docker-compose-app.yml up -d

# 兼容 amd、arm 构建镜像
# docker buildx build --load --platform liunx/amd64,linux/arm64 -t xiaofuge/xfg-frame-archetype-app:1.0 -f ./Dockerfile . --push