#JAR 파일 위치 지정: ARG JAR_FILE=build/libs/*.jar 대신 명확한 JAR 파일 이름을 지정하여 예상치 못한 파일이 복사되는 것을 방지합니다. 실제 JAR 파일의 이름이나 경로가 다를 경우 적절히 조정해야 합니다.
#환경 변수 설정: 필요한 경우, Java 옵션 또는 애플리케이션 관련 환경 변수를 ENV를 통해 설정할 수 있습니다.
#포트 설정: 애플리케이션이 특정 포트를 리스닝하는 경우, EXPOSE 명령어를 사용하여 해당 포트를 명시할 수 있습니다.
#추가 설정: 필요에 따라 파일 시스템 권한, 로깅 설정, 모니터링 도구 설정, 리소스 한계 설정 등을 추가할 수 있습니다.

# 1. 기본 이미지 선택
FROM openjdk:17-alpine

RUN addgroup -S spring && adduser -S spring -G spring

# logs 디렉토리 생성 및 권한 설정
RUN mkdir -p /logs && touch /logs/sean.log && chown -R spring:spring /logs && chmod -R 755 /logs

USER spring:spring

# 3. 애플리케이션 JAR 파일의 위치를 인자로 받음
COPY build/libs/jsvill-0.0.1-SNAPSHOT.jar app.jar

# 4. 애플리케이션 실행
ENTRYPOINT ["java","-jar","/app.jar"]


