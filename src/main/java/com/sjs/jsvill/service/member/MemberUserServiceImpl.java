package com.sjs.jsvill.service.member;

import com.sjs.jsvill.dto.member.MemberAdminDTO;
import com.sjs.jsvill.dto.member.MemberUserDTO;
import com.sjs.jsvill.entity.MemberAdmin;
import com.sjs.jsvill.entity.MemberUser;
import com.sjs.jsvill.repository.MemberAdminRepository;
import com.sjs.jsvill.repository.MemberUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberUserServiceImpl implements MemberUserService {

    private final MemberUserRepository memberUserRepository;

    @Override
    public Long register(MemberUserDTO dto) {
        log.info("member admin dto");
        MemberUser memberUser = dtoToEntity(dto);
        memberUserRepository.save(memberUser);
        return memberUser.getMemberuser_rowid();
    }
}
