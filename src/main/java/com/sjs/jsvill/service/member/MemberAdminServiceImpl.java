package com.sjs.jsvill.service.member;

import com.sjs.jsvill.dto.member.MemberAdminDTO;
import com.sjs.jsvill.entity.MemberAdmin;
import com.sjs.jsvill.repository.MemberAdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberAdminServiceImpl implements MemberAdminService {

    private final MemberAdminRepository memberAdminRepository;

    @Override
    public Long register(MemberAdminDTO dto) {
        log.info("member admin dto");
        MemberAdmin memberAdmin = dtoToEntity(dto);
        memberAdminRepository.save(memberAdmin);
        return memberAdmin.getMemberadmin_rowid();
    }
}
