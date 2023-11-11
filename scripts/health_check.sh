# health_check.sh

#!/bin/bash
ROOT_PATH="/home/ubuntu/spring-github-action" # 프로젝트 루트
SERVICE_URL="$ROOT_PATH/service_url.inc"
ERROR_LOG="$ROOT_PATH/error.log"
START_LOG="$ROOT_PATH/start.log"
NOW=$(date +%c)
# service_url.inc 에서 현재 서비스를 하고 있는 WAS의 포트 번호 가져오기
CURRENT_PORT=$(cat $SERVICE_URL | grep -Po '[0-9]+' | tail -1)
TARGET_PORT=0

if [ ${CURRENT_PORT} -eq 8081 ]; then
    TARGET_PORT=8082
elif [ ${CURRENT_PORT} -eq 8082 ]; then
    TARGET_PORT=8081
else
    echo "[$NOW] No WAS is connected to nginx" >> $ERROR_LOG
    exit 1
fi


# 위 커맨드들을 통해 현재 타겟포트 가져오기
echo "[$NOW] Start health check of WAS at 'http://127.0.0.1:${TARGET_PORT}' ..." >> $START_LOG

# 아래 커맨드들을 새로 열린 서버가 정상적으로 작동하는지 확인
# 해당 커맨드들을 10번씩 반복
# 원래는 for RETRY_COUNT in {1..10}였으나, Bash 버전의 문제인지 1번만 실행되는 오류가 있음
for RETRY_COUNT in 1 2 3 4 5 6 7 8 9 10
do
    echo "[$NOW] #${RETRY_COUNT} trying..." >> $START_LOG
    # 테스트할 API 주소를 통해 http 상태 코드 가져오기
    RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}"  http://127.0.0.1:${TARGET_PORT}/member/login 2>> $START_LOG)

	# RESPONSE_CODE의 http 상태가 200번인 경우
    if [ ${RESPONSE_CODE} -eq 200 ]; then
        echo "[$NOW] New WAS successfully running" >> $START_LOG
        exit 0
    elif [ ${RETRY_COUNT} -eq 10 ]; then
        echo "[$NOW] Health check failed." >> $ERROR_LOG
        exit 1 #exit 1이 호출되면 스크립트의 실행이 중단되고, 이에 따라 switch.sh 스크립트도 실행되지 않습니다.
    fi
    # 아직 열려있지 않았다면 sleep
    echo "[$NOW] RESPONSE_CODE $RESPONSE_CODE / Not yet...sleep 40" >> $START_LOG
    sleep 40
done