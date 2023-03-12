## 참고
- [JWT Easy ](https://github.com/codingspecialist/Springboot-JWT-React-OAuth2.0-Eazy/tree/master/Springboot-Security-JWT-Easy)

## 수정 & 특이 사항
### 특이사항
- 스프링 시큐리티 버전 변경으로 인한 변화 : `extends WebSecurityConfigurerAdapter` -> `public SecurityFilterChain filterChain(HttpSecurity http)`
- Maven -> Gradle
- Authorization Code로 Google에게 UserInfo(ProviderId,Email,Profile-Img 등) 요청
- 해당 정보를 가지고 DB에 저장한 후, 스프링 서버 만의 JWT 토큰 발급

## 추후 업데이트 사항
- JWT Refresh Token 사용까지

