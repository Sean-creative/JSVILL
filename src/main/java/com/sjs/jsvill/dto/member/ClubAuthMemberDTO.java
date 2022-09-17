package com.sjs.jsvill.dto.member;

import com.sjs.jsvill.entity.enm.MemberRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;

@Log4j2
@Getter
@Setter
@ToString
public class ClubAuthMemberDTO extends User {
//DTO 역할을 수행하는 클래스인 동시에 스프링 시큐리티에서 인가/인증 작업에 사용할 수 있다.
//사용자의 정보를 가져오는 핵심적인 역할을 하는 UserDetailsService라는 인터페이스 때문에, DTO 타입으로 처리했다.

    private Long memberRowid;
    private String phoneNumber;
    private String pinNumber;
    private boolean fromSocial;
    private String name;
    private String email;
    private Set<MemberRole> roleSet;

    public ClubAuthMemberDTO(String phoneNumber, String pinNumber, boolean fromSocial,
                             String name, String email,
                             Collection<? extends GrantedAuthority> authorities) {
        super(phoneNumber, pinNumber, authorities);
        this.fromSocial = fromSocial;
        this.name = name;
        this.email = email;
    }

}
