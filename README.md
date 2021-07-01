# 注意
    该项目将不再维护脚手架部分内容
# halink-boot
    SpringBoot整合常用的框架，规范编码习惯，提供项目业务开发前的基础支撑
# 需要修改内容
    将```halink-boot```进行全局替换
    将包名进行适当调整
# build & run
    docker build -t halink-boot .
    docker run -itd --name halink-boot-test -p 8080:8080 --e spring.profiles.active=test halink-boot

# 项目介绍
    接口 RESTful API 设计规范 https://godruoyi.com/posts/the-resetful-api-design-specification
