# JSVill

## Description

- 🔗링크 : www.jsvill.com


- ### 🔊 저희 JSVILL은요... (프로젝트 소개)
  - 임대인과 임차인에게 필요한 기능을 제공하고 불편함을 해소해 주는 임대 관리 서비스입니다.
  - 실제 임대인으로서 사용하기 위해 혼자서 모든개발을 진행하고 있습니다.
  - 이 서비스는 건물관리, 일정관리, 공실관리, 계약서 관리 등의 기능을 지원합니다.
  - 사용자 요구 분석 및 프로젝트 개발 기간 단축을 위해 데모 버전을 활용한 사용자와의 정기 미팅 진행하여 애자일하게 개발하고 있습니다.
  - Kubernetes, Monitoring(Prometheus, Grafana, Promtail, Loki), Argo CD, Kafka와 같은 다양한 기술을 접목시켰습니다.




- ### 🏠 구현된 기능 (2024.03 기준)
  - 로그인, 핸드폰 인증을 통한 회원가입, 핀번호 찾기, 로그아웃
  - 건물 관리
    - 해당 건물의 일정 관리 (반복 일정 등록, 미완료 일정 확인 등)
    - 캘린더 일정 마감 알림 (앞으로 다가올 일정에 대한 알림)
    - 카카오맵을 사용한 주소 검색
  - 호실 관리
    - 특정 호실에 대한 메모, 차량, 사진(계약서) 관리
    - 입주자에게 SMS 전송
    - 계약 즉시 만료 기능
    - 이전 계약 내역 조회
  - 커뮤니티 기능


- ### 🏠 구현된 아키텍처 (2024.03 기준)
  - AWS EC2 기반에서 GKE를 이용한 Kubernetes 환경으로 전환
  - Helm과 Kustomize를 이용해 매니페스트 파일을 중앙 집중식으로 관리
  - GitHub Actions와 Argo CD를 사용한 CI/CD 파이프라인을 재구성
  - Prometheus, Prometheus Operator, Grafana, 및 Spring Actuator를 사용해 서버 상태 시각화 및 실시간 모니
터링 시스템을 구축
  - Kubernetes 환경의 분산 로그를 LogBack과 Promtail을 통해 Loki로 중앙집중화
  - Grafana의 Alert과 Discord를 연동해 실시간 시스템 이슈 감지 및 경고 시스템 구현
  - Spring Scheduled, Kafka, 및 SSE-Emitter를 사용하여 대량 캘린더 일정 마감 알림을 처리하고, KafkaUI로 관리를 최적화한 신뢰성 및 확장성 높은 알림 시스템 구축
  - total 아키텍처 사진!!!!!
  

  

- ### 😊 구현될 기능 & 방향
  - 주차관리 (기획&개발)
  - 코프링으로의 전환 (SpringBoot + Kotlin)
  - 연체관리
    



- ### 🛠️사용기술
  - Java, Spring Boot, Spring Security, JPA, QueryDSL, MariaDB
  - Kubernetes(GKE), Helm, Kustomize, Argo CD, GitHub Actions, AWS Route 53
  - Prometheus, Grafana, Promtail, Loki
  - Thymeleaf, HTML, JavaScript, CSS
  - Junit5, Mockito
  - 도메인 - HOSTING.KR



  
- 💻구동화면 (실제 데이터가 아닙니다)


<div style="text-align:center">
    <img width="400" alt="image" src="https://jsvill.s3.ap-northeast-2.amazonaws.com/git/Screenshot_40.png">
    <img width="400" alt="image" src="https://jsvill.s3.ap-northeast-2.amazonaws.com/git/Screenshot_41.png" style="margin-left:10px">
    <img width="820" alt="image" src="https://jsvill.s3.ap-northeast-2.amazonaws.com/git/Screenshot_44.png" style="margin-top:10px">
    <img width="820" height="700" alt="image" src="https://github.com/Sean-creative/JSVILL/assets/49125201/7760f337-8e09-4f73-9145-c55e42487d60" style="margin-top:10px">
    <img width="820" alt="image" src="https://jsvill.s3.ap-northeast-2.amazonaws.com/git/Screenshot_43.png" style="margin-top:10px">
  <img width="820" alt="image" src="https://jsvill.s3.ap-northeast-2.amazonaws.com/git/Screenshot_43.png" style="margin-top:10px">
  <img  alt="image" src="https://github.com/Sean-creative/JSVILL/assets/49125201/07b69a31-9678-4a18-a4a3-9a33258e9d82" style="margin-top:10px">
  <img  alt="image" src="https://github.com/Sean-creative/JSVILL/assets/49125201/d9d216f4-202f-44b1-aa6c-0e06fae3f103">
</div>



---

<details><summary>📖 Version 업데이트 내역 보기</summary>

### _v1.9.0 Release [latest] [23.10.20]_
- PIN 번호 타입 수정 & 계약 수정 시 날짜 오류 수정 [#1](https://github.com/Sean-creative/JSVILL/issues/1)
- 계약서(photo) 업로드의 다양한 기능 구현 [#2](https://github.com/Sean-creative/JSVILL/issues/2)
  - 북마크 기능 구현
  - 사진 삭제 기능 구현
    - DB 뿐만아니라 s3에서도 삭제가 되어야함
  - 사진 업로드시, 기존 사진 둘 다 적용
  - 사진 업로드 이미지 크기 늘림
- 달력의 내용 수정 & 호실수정 시 '호'제거 & 호실의 오름차순 및 레이아웃 수정 [#3](https://github.com/Sean-creative/JSVILL/issues/3)
- 호실 이름 오류 수정 & 만나이가 아닌 실제나이 사용으로 수정 [#4](https://github.com/Sean-creative/JSVILL/issues/4)

---

### _v1.8.0 Release [23.09.20]_
- s3를 활용한 계약서 사진등록 기능 추가
  - 여러개의 사진 첨부도 가능 
  - 파일 선택 or 드래그앤 드롭 기능 
  - 이미지 클릭 시 크게 보기 기능

---

### _v1.7.0 Release  [23.08.20]_
- 캘린더 기능 추가
  - 시간제 or 종일 기능
  - 반복 일정 기능 
  - 미완료 일정 안내

---


### _v1.6.0 Release [23.07.20]_
- 커뮤니티 기능 추가
- 세입자의 나이 입력 기능 추가

---

### _v1.5.0 Release [23.06.20]_
- 호실의 메모 기능 추가
- 계약-세입자의 차량등록 추가

---

### _v1.4.1 Release [23.05.30]_
- 건물, 호실, 계약이 하나도 없을 때 오류 수정
- 건물관리의 호실에 대한 요약 정보 수정
- 로그인 화면의 PIN 번호 입력 기능 수정

---

### _v1.4.0 Release [23.05.20]_
- 세입자에게 SMS 보내기 기능 추가

---

### _v1.3.0 Release [23.04.20]_
- 스프링 시큐리티 적용
- 로그인 기능 추가
  - 컨트롤러에서 로그인한 정보 사용
- SMS을 활용한 회원가입, 비밀번호 찾기 기능 추가

--- 

### _v1.2.0 Release [23.03.20]_
- 계약 만료 모달 기능 추가
- 카카오맵 지도검색 기능 추가(건물 등록 시 사용) 

--- 

### _v1.1.0 Release [23.03.07]_
- 계약의 입주자가 유니크하도록 기능 추가
- 계약의 입주자 리스트에서 세대주가 제일 먼저 나오도록 수정

--- 

### _v1.0.0 Release [23.03.01]_
- 첫 릴리즈
- 기본적인 기능 (건물, 호실, 계약)
</details>

---
