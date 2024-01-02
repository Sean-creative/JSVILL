#JAR 파일 위치 지정: ARG JAR_FILE=build/libs/*.jar 대신 명확한 JAR 파일 이름을 지정하여 예상치 못한 파일이 복사되는 것을 방지합니다. 실제 JAR 파일의 이름이나 경로가 다를 경우 적절히 조정해야 합니다.
#환경 변수 설정: 필요한 경우, Java 옵션 또는 애플리케이션 관련 환경 변수를 ENV를 통해 설정할 수 있습니다.
#포트 설정: 애플리케이션이 특정 포트를 리스닝하는 경우, EXPOSE 명령어를 사용하여 해당 포트를 명시할 수 있습니다.
#추가 설정: 필요에 따라 파일 시스템 권한, 로깅 설정, 모니터링 도구 설정, 리소스 한계 설정 등을 추가할 수 있습니다.


# 1. 기본 이미지 선택
FROM openjdk:16-alpine

# 2. root 권한으로 실행되지 않도록 사용자 그룹 추가.
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# 3. 애플리케이션 JAR 파일의 위치를 인자로 받음
ARG JAR_FILE=build/libs/jsvill-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# 4. 애플리케이션 실행
ENTRYPOINT ["java","-jar","/app.jar"]

# 추가 사항들 (필요에 따라 적용)
# 환경 변수 설정
# ENV JAVA_OPTS=""

# 포트 설정 (애플리케이션이 사용하는 포트가 있다면)
# EXPOSE 8080

# 파일 시스템 권한 설정, 로깅, 모니터링, 리소스 한계 설정 등
