package com.sjs.jsvill.service.member;

import com.sjs.jsvill.dto.member.ClubAuthMemberDTO;
import com.sjs.jsvill.dto.member.MemberDTO;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입 -> repository가 자동 주입
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final MemberRepository memberRepository; //반드시 final로 선언

    @Override
    public Optional<Member> findByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber, false);
    }

    @Override
    public Long register(MemberDTO dto) {
        //log.info("DTO-------------" );
        //log.info(dto);
        Member member = dtoToEntity(dto);
        Member returnMember = memberRepository.save(member);
        return returnMember.getMemberRowid();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("ClubUserDetailsService loadUserByUsername " + username);
        Optional<Member> result = memberRepository.findByPhoneNumber(username, false);

        if(result.isEmpty()){
            throw new UsernameNotFoundException("Check User Email or from Social ");
        }

        Member member = result.get();

        log.info("-----------------------------");
        log.info(member);

        ClubAuthMemberDTO clubAuthMemberDTO = new ClubAuthMemberDTO(
                member.getPhoneNumber(),
                member.getPinNumber(),
                member.isFromSocial(),
                member.getName(),
                member.getEmail(),
                member.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                        .collect(Collectors.toSet())
        );

        return clubAuthMemberDTO;
    }


//    @Override
//    public MemberUserLoginDTO login(String phone, MemberLoginDTO memberUser) {
//        MemberUser user = memberUserRepository.findByPhoneAndPin(memberUser.getPhone(), memberUser.getPin());
//        MemberUserLoginDTO loginDto;
//        if(user != null) {
//            loginDto = entityToDto(user);
//        }else {
//            loginDto = new MemberUserLoginDTO();
//        }
//
//        return loginDto;
//    }
}
