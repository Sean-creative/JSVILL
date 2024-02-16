package com.sjs.jsvill.dto.member;

import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity.enm.MemberRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Getter
@Setter
@ToString
public class MemberDTO extends User {
//DTO 역할을 수행하는 클래스인 동시에 스프링 시큐리티에서 인가/인증 작업에 사용할 수 있다.
//사용자의 정보를 가져오는 핵심적인 역할을 하는 UserDetailsService라는 인터페이스 때문에, DTO 타입으로 처리했다.

    private Long memberRowid;
    private String phoneNumber;
    @Length(min = 4, max = 4, message = "핀번호는 숫자 4자리 입니다.")
    private String pinNumber;
    private boolean fromSocial;
    private String name;
    private String email;
    private Set<MemberRole> roleSet;



    public MemberDTO(Long memberRowid, String phoneNumber, String pinNumber, Collection<? extends GrantedAuthority> authorities) {
        super(phoneNumber, pinNumber, authorities);
        this.phoneNumber = phoneNumber;
        this.pinNumber = pinNumber;
        this.memberRowid = memberRowid;
    }



    //    public MemberDTO(String phoneNumber, String pinNumber, Collection<? extends GrantedAuthority> authorities) {
    public MemberDTO(Member member) {
        super(member.getPhoneNumber(), member.getPinNumber(), member.getRoleSet().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                .collect(Collectors.toSet()));
        this.memberRowid = member.getMemberRowid();
        this.phoneNumber = member.getPhoneNumber();
        this.pinNumber = member.getPinNumber();
        this.fromSocial = member.isFromSocial();
        this.name = member.getName();
        this.email = member.getEmail();
        this.roleSet = member.getRoleSet();
    }
}
