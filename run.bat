@ECHO off

ECHO Select command;
ECHO 1) Build
ECHO 2) Generate docker images
ECHO 3) Run

SET /p command=

IF /i "%command%"=="1" GOTO build
IF /i "%command%"=="2" GOTO generate
IF /i "%command%"=="3" GOTO run

:build
mvn clean package
GOTO commonExit

:generate
docker-compose down
docker image rm sds-eureka
docker image rm sds-scheduler
docker image rm sds-postgres
docker build --tag=sds-eureka:latest ./sds-eureka
docker build --tag=sds-scheduler:latest ./sds-scheduler
docker build --tag=sds-postgres:latest ./sds-postgres
GOTO commonExit

:run
docker-compose up --scale sds-scheduler=3
GOTO commonExit

:commonExit
exit