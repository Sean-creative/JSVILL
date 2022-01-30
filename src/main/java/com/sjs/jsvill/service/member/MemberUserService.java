package com.sjs.jsvill.service.member;

import com.sjs.jsvill.dto.member.MemberAdminDTO;
import com.sjs.jsvill.dto.member.MemberUserDTO;
import com.sjs.jsvill.entity.MemberAdmin;
import com.sjs.jsvill.entity.MemberUser;

public interface MemberUserService {
    Long register(MemberUserDTO dto);

    default MemberUser dtoToEntity(MemberUserDTO dto) {
        MemberUser memberUser = MemberUser.builder()
                .member_rowid(dto.getMemberR())
                .phone(dto.getPhone())
                .pin(dto.getPin())
                .build();
        return memberUser;
    }
}
