package com.sjs.jsvill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing //JPA-AuditionEntityListener를 활성, 자동으로 시간처리
@EnableScheduling // 주기적인 heartbeat, calendar 알림설정
public class JsvillApplication {

	//EC2 인스턴스가 아닌 환경에서 실행할 때에 발생하는 의미없는 에러를 없애줌
	//대신 서비스 실행 시 오류구문이 발생하긴함
	static {
		System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
	}
	public static void main(String[] args) {

		SpringApplication.run(JsvillApplication.class, args);
	}
}
