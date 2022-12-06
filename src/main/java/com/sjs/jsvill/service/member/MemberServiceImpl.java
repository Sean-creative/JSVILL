package com.sjs.jsvill.service.member;

import com.sjs.jsvill.dto.member.ClubAuthMemberDTO;
import com.sjs.jsvill.dto.member.SignUpPinNewDTOReq;
import com.sjs.jsvill.dto.member.SignUpPinOldDTOReq;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입 -> repository가 자동 주입
public class MemberServiceImpl implements MemberService, UserDetailsService {

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository; //반드시 final로 선언

    @Override
    public Optional<Member> findByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber, false);
    }

    @Override
    public void register(SignUpPinNewDTOReq req) {
        req.setPinNumber(passwordEncoder.encode(req.getPinNumber()));
        memberRepository.save(dtoReqToEntity(req));
    }

    @Transactional
    @Override
    public void modify(SignUpPinOldDTOReq req) {
        Optional<Member> result = findByPhoneNumber(req.getPhoneNumber());
        Member member = result.get();
        member.setPinNumber(passwordEncoder.encode(req.getPinNumber()));
        memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {

        log.info("ClubUserDetailsService loadUserByUsername " + phoneNumber);
        Optional<Member> result = memberRepository.findByPhoneNumber(phoneNumber, false);

        if(result.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
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
