package com.sjs.jsvill.service.jimmy.member;

import com.sjs.jsvill.dto.jimmy.member.MemberLoginDTO;
import com.sjs.jsvill.dto.jimmy.member.MemberUserDTO;
import com.sjs.jsvill.dto.jimmy.member.MemberUserLoginDTO;
import com.sjs.jsvill.entity.sean.Member;
import com.sjs.jsvill.entity.jimmy.MemberUser;
import com.sjs.jsvill.repository.sean.MemberRepository;
import com.sjs.jsvill.repository.jimmy.MemberUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberUserServiceImpl implements MemberUserService {

    private final MemberUserRepository memberUserRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long register(MemberUserDTO dto) {
        log.info("member admin dto");
        Member member = memberRepository.getById(dto.getMemberR());
        MemberUser memberUser = MemberUser.builder()
                    .member(member)
                    .phone(dto.getPhone())
                    .pin(dto.getPin())
                    .build();
        //MemberUser memberUser = dtoToEntity(dto);
        memberUserRepository.save(memberUser);
        return memberUser.getMemberuser_rowid();
    }

    @Override
    public MemberUser get(String phone) {
        MemberUser user = memberUserRepository.findByPhone(phone);
        return user;
    }

    @Override
    public MemberUserLoginDTO login(String phone, MemberLoginDTO memberUser) {
        MemberUser user = memberUserRepository.findByPhoneAndPin(memberUser.getPhone(), memberUser.getPin());
        MemberUserLoginDTO loginDto;
        if(user != null) {
            loginDto = entityToDto(user);
        }else {
            loginDto = new MemberUserLoginDTO();
        }

        return loginDto;
    }
}
