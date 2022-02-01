package com.sjs.jsvill.service.member;

import com.sjs.jsvill.dto.member.MemberAdminDTO;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity.MemberAdmin;
import com.sjs.jsvill.entity._Salt;
import com.sjs.jsvill.repository.MemberAdminRepository;
import com.sjs.jsvill.repository.MemberRepository;
import com.sjs.jsvill.repository._SaltRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberAdminServiceImpl implements MemberAdminService {

    private final MemberAdminRepository memberAdminRepository;
    private final MemberRepository memberRepository;
    private final _SaltRepository saltRepository;

    @Override
    public Long register(MemberAdminDTO dto) {
        log.info("member admin dto");
        Member member = memberRepository.getById(dto.getMemberR());
        _Salt salt = saltRepository.getSaltById(dto.getSaltId());
        MemberAdmin memberAdmin = MemberAdmin.builder()
                .member(member)
                .userid(dto.getUserId())
                .pw(dto.getPw())
                .salt(salt)
                .build();
        memberAdminRepository.save(memberAdmin);
        return memberAdmin.getMemberadmin_rowid();
    }
}
