# JSVill

## Description

- 🔗링크 : <a href="www.jsvill.com" target="_blank">jsvill</a>


- ### 🔊 저희 JSVILL은요... (프로젝트 소개)
  - 임대인과 임차인의 필요한 기능 제공 및 불편함을 해소해주는 임대관리 서비스입니다.
  - 건물관리, 공실관리, 계약서 관리 등의 기능을 지원하고 있습니다.
  - 실제 임차인으로서 사용하기 위해 기획 부터 개발까지 혼자서 진행하고 있습니다.
  - 사용자와 데모버전을 사용한 미팅을 통해 엔드 유저의 요구 사항 분석해 가며 애자일하게 개발하고 있습니다.
    - 요구사항 분석능력 향상 및 프로젝트의 개발시간 단축에 많은 도움을 주고 있습니다.




- ### 🏠 구현된 기능 (2023.09 기준)
  - 로그인, 핸드폰 인증을 통한 회원가입, 핀번호 찾기, 로그아웃
  - 건물 관리
    - 해당 건물에 대한 일정관리 (반복일정 등록, 미완료 일정 확인 등)
    - 카카오맵을 활용한 주소검색
  - 호실 관리
    - 해당 호실에 대한 메모, 차량, 사진(계약서) 관리
    - 입주자에게 sms 보내기
    - 계약 즉시 만료
    - 이전 계약 내역 보기
  - 커뮤니티 기능
    



- ### 😊 구현될 기능 & 방향
  - 캘린더의 일정 알림 (앞으로 다가올 일정에 대한 알림)
  - 주차관리 (기획&개발)
  - 코프링으로의 전환 (SpringBoot + Kotlin)
  - 연체관리
  - Spring Batch
    



- ### 🛠️사용기술
  - Java17, Spring Boot, Spring Security, Spring Data JPA, querydsl
  - MySQL, MariaDB
  - HTML, JavaScript, CSS
  - Junit5, Mockito, Spock
  - AWS EC2, AWS Route 53
  - 도메인 - HOSTING.KR



  
- 💻구동화면 (실제 데이터가 아닙니다)


<div style="text-align:center">
    <img width="400" alt="image" src="https://jsvill.s3.ap-northeast-2.amazonaws.com/git/Screenshot_40.png">
    <img width="400" alt="image" src="https://jsvill.s3.ap-northeast-2.amazonaws.com/git/Screenshot_41.png" style="margin-left:10px">
    <img width="820" alt="image" src="https://jsvill.s3.ap-northeast-2.amazonaws.com/git/Screenshot_44.png" style="margin-top:10px">
    <img width="820" height="700" alt="image" src="https://jsvill.s3.ap-northeast-2.amazonaws.com/git/Screenshot_42.png" style="margin-top:10px">
    <img width="820" alt="image" src="https://jsvill.s3.ap-northeast-2.amazonaws.com/git/Screenshot_43.png" style="margin-top:10px">
</div>



---

<details><summary>📖 Version 업데이트 내역 보기</summary>


### _v1.8.0 Release [latest] [23.09.20]_
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
