package com.sjs.jsvill.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        return new CustomAuthenticationProvider();
//    }

    @Override
    // js, css, image 설정은 보안 설정의 영향 밖에 있도록 만들어주는 설정.
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    // BCryptPasswordEncoder는 Spring Security에서 제공하는 비밀번호 암호화 객체입니다.
    // Service에서 비밀번호를 암호화할 수 있도록 Bean으로 등록합니다.
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.rememberMe().tokenValiditySeconds(60 * 60 * 24 * 3).userDetailsService(userDetailsService); //3days
        http.csrf().disable();  //음.. 나중에 적용하면 좋나?
        http.authorizeRequests()
//                .antMatchers("/home").permitAll()
                .antMatchers("/sample/member").hasRole("USER")
                .antMatchers("/home").hasRole("USER")
                .anyRequest().authenticated();


        //인가/인증에 문제시 로그인 화면
//        loginPage() : 불러올 로그인 페이지
//        loginProcessingUrl() : 로그인 정보를 보낼 액션 페이지
//        defaultSuccessUrl() : 로그인 성공 시 보낼 페이지
        http.formLogin()
                .loginPage("/member/login") //이걸로 별도의 로그인 페이지 이용
                .usernameParameter("phoneNumber")
                .passwordParameter("pinNumber")
                .loginProcessingUrl("/login_proc")
                .defaultSuccessUrl("/home")
                .permitAll();

        http.logout() //로그아웃 했을 때 지정해놓은 페이지 볼 수 있음
                .logoutUrl("/member/login");
    }
}
