# Spring Security Login,Signup

### 프로젝트 구조
![se](https://user-images.githubusercontent.com/65895403/103454952-0e5fef00-4d2c-11eb-9b97-ba44a774c717.PNG)


## 1. 의존성 추가
Spring Security를 사용하려면 , 의존성을 추가해줘야 함 ,Thymeleaf에서 Spring Security 통합 모듈을 사용하기위한 의존성도 추가해줘야함!!
#### build.gradle
``` java
implementation 'org.springframework.boot:spring-boot-starter-security'
implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5'
```
## 2. Spring Security 설정

Spring Security는 FilterChainProxy라는 이름으로 내부에 여러 Filter들이 동작하고 있습니다. 별도의 로직을 작성 안해도 로그인/로그아웃 등의 처리가 가능  
설정은 WebSecurityConfigurerAdapter라는 클래스를 상속받은 클래스에서 메서드를 오버라이딩하여 조정가능,

<img src="https://godekdls.github.io/images/springsecurity/securityfilterchain.png">

### <a href="https://github.com/jhj960918/Luxury_Shopping/blob/master/src/main/java/com/jin/ecommerce/config/WebSecurityConfig.java">WebSecurityConfig.java 코드</a>

### <a href="https://github.com/jhj960918/Luxury_Shopping/blob/master/src/main/java/com/jin/ecommerce/Controller/MemberController.java">MemberController.java 코드</a>

### <a href="https://github.com/jhj960918/Luxury_Shopping/blob/master/src/main/java/com/jin/ecommerce/domain/entity/MemberEntity.java">MemberEntity.java 코드</a>

### <a href="https://github.com/jhj960918/Luxury_Shopping/blob/master/src/main/java/com/jin/ecommerce/domain/repository/MemberRepository.java">Memberrepository.java 코드</a>

### <a href="https://github.com/jhj960918/Luxury_Shopping/blob/master/src/main/java/com/jin/ecommerce/dto/MemberDto.java">MemberDto.java 코드</a>

### <a href="https://github.com/jhj960918/Luxury_Shopping/blob/master/src/main/java/com/jin/ecommerce/service/MemberService.java">MeberService.java 코드</a>



## 5. [실행결과]

로그인 -> 아이디 비밀번호 입력 -> 메인페이지 이동
![1212](https://user-images.githubusercontent.com/65895403/103455405-305b7080-4d30-11eb-8a7a-ed312e122a2e.PNG)

#### view 구현은 천천히 할게요
