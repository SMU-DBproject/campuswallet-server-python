# 🧩 Campus Wallet
대학생 생활비 분석 및 절약 추천 시스템
숙명여자대학교 2025년 1학기 데베프 팀 프로젝트
<br/><br/><br/><br/>

## 🚀 프로젝트 개요
본 프로젝트는 이용자(대학생)의 생활비 지출 데이터를 분석하여  
효율적인 예산 관리를 돕고, 개인 맞춤형 할인 정보를 추천하는 시스템입니다.
<br/>
학생들이 자신의 소비 패턴을 파악하고,  
예산 초과 카테고리를 알림으로 받아 절약에 도움을 받을 수 있도록 설계되었습니다.
<br/><br/><br/><br/>

## 📑 주요 기능
- **개인 정보 관리**  
  로그인, 회원가입, 예산 설정, 마이페이지 내 정보 수정
  <br/>
- **소비내역 관리**  
  개인 소비 내역 조회/입력/수정/삭제
<br/>
- **소비내역 분석**  
  카테고리별 소비내역 조회, 최대 지출 카테고리 조회, 예산 초과 카테고리 조회
<br/>
- **할인 정보 추천**  
  예산 초과 카테고리 항목에 대해 할인 정보 추천 목록 조회
<br/><br/><br/><br/>

## 📅 프로젝트 진행 일정
- **1주차 : DB 설계 및 기반 구축**

|업무|담당자|
| -------------- | -------- |
|ERD 생성|강민채|
|DB 설계|강민채|
|Cloud 연동|홍서연|
|Entity 구현|홍서연|

- **2주차 : 페이지 구현**

|업무|담당자|
| ------------------------------------------------ | -------- |
|로그인 구현|김문원|
|소비내역 조회/입력/삭제/수정, 카테고리별 분석 기능 구현|김민경|
|카테고리 예산 초과 경고 기능 구현|홍서연|
|할인 추천 기능 구현|강민채|

- **3주차 : 기능 통합 및 Front 작업**

|업무|담당자|
| -------------------------------------- | -------- |
|기능 통합, test, 보고서 작성|홍서연|
|front 작업 및 연동|김문원, 김민경|
|PPT 작성 및 프레젠테이션 준비|강민채|

<br/><br/><br/><br/>

## 👩‍💻 기술 스택
- **Backend**: Java, Spring Boot
- **Frontend**: TypeScript, React
- **Database**: Oracle Autonomous DB (Cloud)
- **빌드 도구**: Gradle
<br/><br/><br/><br/>

## 📑 시스템 아키텍처
사용자(클라이언트)
↓
Spring Boot REST API (Controller → Service → Repository)
↓
Oracle Autonomous DB (View, Procedure, Trigger 활용)
<br/><br/><br/><br/>

## ⚙️ 주요 모듈 및 패키지 구조
- `domain.auth`  
  사용자 정보 관리
  
- `domain.budgetalert`  
  예산 초과 알림 및 예산 초과 카테고리 조회 관련 기능

- `domain.category`  
  카테고리별 소비내역 조회, 최대 지출 카테고리 조회
  
- `domain.discount`  
  할인 정보 추천 기능

- `domain.spending`  
  소비 데이터 관리 기능
<br/><br/><br/><br/>

## 🪛 설치 및 실행 방법
1. Oracle Autonomous DB 인스턴스 생성 및 접속 정보 준비  
2. 로컬에 Oracle Wallet 파일 설정  
3. 프로젝트 클론  
   ```bash
   git clone https://github.com/SMU-DBproject/campuswallet-server-spring.git
4. Gradle로 의존성 설치 및 빌드
5. application.yml에 Oracle DB 접속 정보 설정
6. Spring Boot 애플리케이션 실행
7. API 테스트
<br/><br/><br/><br/>

## 👥 팀원
|이름|전공|github 아이디|
|------|--------|---------------------|
|강민채|통계학과|alsco31|
|김문원|IT|angkmfirefoxygal|
|김민경|컴퓨터과학|yulleta|
|홍서연|컴퓨터과학|SeoyeonH|
