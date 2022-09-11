package com.sjs.jsvill.service.member;

import com.sjs.jsvill.dto.member.MemberDTO;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입 -> repository가 자동 주입
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository; //반드시 final로 선언

    @Override
    public Member findByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber, false).get();
    }

    @Override
    public Long register(MemberDTO dto) {
        //log.info("DTO-------------" );
        //log.info(dto);
        Member member = dtoToEntity(dto);
        Member returnMember = memberRepository.save(member);
        return returnMember.getMemberRowid();
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
