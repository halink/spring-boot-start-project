# startProject
    脚手架
# build & run
    docker build -t start-project .
    docker run -itd --name start-project-test -p 8080:8080 --e spring.profiles.active=test start-project