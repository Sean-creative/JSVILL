# run_new_was.sh

#!/bin/bash

ROOT_PATH="/home/ubuntu/spring-github-action" # 프로젝트 루트
JAR="$ROOT_PATH/application.jar"

SERVICE_URL="$ROOT_PATH/service_url.inc"
APP_LOG="$ROOT_PATH/application.log"
ERROR_LOG="$ROOT_PATH/error.log"
START_LOG="$ROOT_PATH/start.log"

NOW=$(date +%c)

# service_url.inc 에서 현재 서비스를 하고 있는 WAS의 포트 번호 가져오기
CURRENT_PORT=$(cat $SERVICE_URL | grep -Po '[0-9]+' | tail -1)
TARGET_PORT=0

echo "[$NOW] Current port of running WAS is ${CURRENT_PORT}." >> $START_LOG

if [ ${CURRENT_PORT} -eq 8081 ]; then
  TARGET_PORT=8082 # 현재포트가 8081이면 8082로 배포
elif [ ${CURRENT_PORT} -eq 8082 ]; then
  TARGET_PORT=8081 # 현재포트가 8082라면 8081로 배포
else
  echo "[$NOW] Not connected to nginx" >> $START_LOG # nginx가 실행되고 있지 않다면 에러 코드
fi

# 현재 포트의 PID를 불러온다
TARGET_PID = $(sudo lsof -ti tcp:${TARGET_PORT})
echo "[$NOW] TARGET_PID : $TARGET_PID" >> $START_LOG

# PID를 이용해 해당 포트 서버 Kill
if [ ! -z ${TARGET_PID} ]; then
  echo "[$NOW] Kill -9 ${TARGET_PORT}." >> $START_LOG
  sudo kill -9 ${TARGET_PID}
fi

echo "[$NOW] $JAR 복사" >> $START_LOG
cp $ROOT_PATH/build/libs/*.jar $JAR

sudo iptables -A PREROUTING -t nat -i eth0 -p tcp -m tcp --dport 81 -j REDIRECT --to-port 8081
sudo iptables -A PREROUTING -t nat -i eth0 -p tcp -m tcp --dport 82 -j REDIRECT --to-port 8082

# 타켓 포트에 jar파일을 이용해 새로운 서버 실행
echo "[$NOW] > $JAR 실행 $TARGET_PORT Port" >> $START_LOG
nohup java -jar -Dserver.port=${TARGET_PORT} ${JAR} > $APP_LOG 2> $ERROR_LOG &

SERVICE_PID=$(pgrep -f $JAR)
echo "[$NOW] > 서비스 PID: $SERVICE_PID" >> $START_LOG

exit 0