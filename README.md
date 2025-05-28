# 기본 DB 코드
Spring에 Oracle DB연동 했습니다.

## 개발 환경
- 'Java 17'
- 'JDK 17.0.7'
- **IDE** : Eclipse IDE for Enterprise Java and Web Developers(4.23.0)
- **Framework** : Springboot(3.5.0)
- **Database** : Oracle SQL Developer(24.3.1.347.1826), Oracle DB(19c), ojdbc11
- **Cloud** : Oracle Cloud

## 파일 구조 규칙
- src/main/java
  자바 소스 코드가 위치하는 폴더입니다.
  구현 기능에 따라 package를 만들고 아래에 controller, service, repository, domain 등 넣어주세요.

- src/main/resources
  애플리케이션 설정 파일(application.yml), 정적 리소스(css, js), 템플릿(html, thymeleaf) 파일을 보관합니다.
**가급적 application.yml은 수정하지 말아주세요**

- src/test/java
  테스트 코드가 위치하는 폴더입니다.

## 파일 및 폴더 네이밍 규칙
- 자바 클래스는 UpperCamelCase로 작성합니다.
- 리소스 파일은 역할과 용도에 맞게 명확한 이름을 사용합니다.

## 커밋 메세지
무엇을 수정했는지 간략하게 적어주세요.

## README
브랜치에 푸시 후, 수정 사항/구현 기능/오류 에 대해서 다른 팀원이 이해할 수 있게 적어주세요.
