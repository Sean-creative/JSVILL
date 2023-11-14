# switch.sh

#!/bin/bash
ROOT_PATH="/home/ubuntu/spring-github-action" # 프로젝트 루트
SERVICE_URL="$ROOT_PATH/service_url.inc"
ERROR_LOG="$ROOT_PATH/error.log"
START_LOG="$ROOT_PATH/start.log"
NOW=$(date +%c)
# service_url.inc 에서 현재 서비스를 하고 있는 WAS의 포트 번호 가져오기
CURRENT_PORT=$(cat $SERVICE_URL  | grep -Po '[0-9]+' | tail -1)
TARGET_PORT=0

echo "[$NOW] Nginx currently proxies to ${CURRENT_PORT}." >> $START_LOG

if [ ${CURRENT_PORT} -eq 8081 ]; then
    TARGET_PORT=8082
elif [ ${CURRENT_PORT} -eq 8082 ]; then
    TARGET_PORT=8081
else
    echo "[$NOW] No WAS is connected to nginx" >> $ERROR_LOG
    exit 1
fi


#2개의 application이 실행되는것은 과부하 일수도?
# 과거 포트의 PID를 불러온다
OLD_PID = $(sudo lsof -ti tcp:${CURRENT_PORT})

# PID를 이용해 해당 포트 서버 Kill
if [ ! -z ${OLD_PID} ]; then
  echo "[$NOW] Kill -9 ${CURRENT_PORT}." >> $START_LOG
  sudo kill -9 ${OLD_PID}
fi

# 위 커맨드들을 통해 현재 타겟포트 가져오기
# $ service_url.inc 파일을 현재 바뀐 서버의 포트로 변경
echo "set \$service_url http://127.0.0.1:${TARGET_PORT};" | tee $SERVICE_URL

echo "[$NOW] Now Nginx proxies to ${TARGET_PORT}." >> $START_LOG

# nginx를 reload 해준다.
sudo service nginx reload

echo "[$NOW] Nginx reloaded." >> $START_LOG