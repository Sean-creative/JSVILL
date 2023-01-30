package com.sjs.jsvill.config;


import com.sjs.jsvill.service.member.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
@Log4j2
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberServiceImpl memberService;
    @Override
    // js, css, image 설정은 보안 설정의 영향 밖에 있도록 만들어주는 설정.
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .antMatchers("/favicon.ico", "/resources/**", "/error"); //인증을 안해도 접근을 허용
    }

    @Bean
    // BCryptPasswordEncoder는 Spring Security에서 제공하는 비밀번호 암호화 객체
    // Service에서 비밀번호를 암호화할 수 있도록 Bean으로 등록합니다.
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.rememberMe().tokenValiditySeconds(60 * 60 * 24 * 3).userDetailsService(userDetailsService); //3days
        http.csrf().disable();  //음.. 나중에 적용하면 좋나?
        //antMatchers -> 요게 리소스에 들어가기 보다는 컨트롤러에 mapping 걸리는걸 말하는듯?
        http.authorizeRequests()
                .antMatchers("/**").permitAll() //Security 인증 일시 해제
                .antMatchers("/login", "/member/login", "/member/signUpNew", "/member/signUpOld", "/member/signUpAuth", "/member/signUpForm", "/member/signUpPinOld").permitAll()
                .anyRequest().authenticated(); //anyRequest는 antMatchers로 지정한 url 이외의 모든 url을 지정하는 메소드, else같은 느낌임

        http.formLogin()
                .loginPage("/member/login") //불러올 로그인 페이지
                .usernameParameter("phoneNumber")
                .passwordParameter("pinNumber")
                .defaultSuccessUrl("/home"); //로그인 성공 시 보낼 페이지

        http.logout() //로그아웃 했을 때 지정해놓은 페이지 볼 수 있음
                .logoutSuccessUrl("/member/login")
                .invalidateHttpSession(true);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService)
                // 해당 서비스(userService)에서는 UserDetailsService를 implements해서
                // loadUserByUsername() 구현해야함 (서비스 참고)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
