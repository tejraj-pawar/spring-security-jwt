# spring-security-jwt

#### This is a SpringBoot Maven project. Here i have authenticated REST services using JWT token based authentication. for that i have used Spring Boot, Spring Security, MySQL, Spring Data JPA and io.jsonwebtoken.jjwt dependency.

#### Below i have given description of all classes in the project:

- **SpringSecurityJwtApplication.java:** This is a SpringBoot bootstrap class.

- **JwtTokenUtil.java:** This is responsible for performing JWT operations like creation and validation of JWT.

- **JwtAuthenticationEntryPoint.java:** This class will extend Spring's AuthenticationEntryPoint class and override its method to commence. It rejects every unauthenticated request and sends error code 401 with user defined error message.
                                    
- **WebSecurityConfig.java:** This class extends the WebSecurityConfigurerAdapter. 
                          This is a convenience class that allows customization to both WebSecurity and HttpSecurity.
                          In this i have set http security to not to authenticate "/authenticate" and "/register" requests.
                          and all other requests must pass through JwtRequestFilter to validate their JWT token.
                        
- **JwtUserDetailsService.java:** This class is implementing UserDetailsService interface and it is responsible for 
                              providing user details for given username.
                              
- **JwtAuthenticationController.java:** This is rest controller class. here we have mapped three resources "/authenticate", "/register" 
                                    and "/hello" to respective methods.
                                   
- **JwtRequestFilter.java:** This class extends the Spring Web Filter OncePerRequestFilter class. For any incoming request, this Filter class gets executed. It checks if the request has a valid JWT token. If it has a valid JWT Token, then it sets the authentication in spring security context to specify that the current user is authenticated.
                         
 - **JwtRequest.java:** This class is required for storing the username and password we received from the client.
 
 - **JwtResponse.java:** This class is required for creating a response containing the JWT to be returned to the user.
 
 - **User.java:** This class is used for ORM for querying user table in mysql DB.
 
 - **UserJpaRepository.java:** This interface extends JpaRepository interface and provide methods to perform operations on user table in MySQL DB.
