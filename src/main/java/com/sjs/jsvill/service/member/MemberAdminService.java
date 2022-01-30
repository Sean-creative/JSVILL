package com.sjs.jsvill.service.member;

import com.sjs.jsvill.dto.member.MemberAdminDTO;
import com.sjs.jsvill.entity.MemberAdmin;

public interface MemberAdminService {
    Long register(MemberAdminDTO dto);

    default MemberAdmin dtoToEntity(MemberAdminDTO dto) {
        MemberAdmin memberAdmin = MemberAdmin.builder()
                .member_rowid(dto.getMemberR())
                .userid(dto.getUserId())
                .pw(dto.getPw())
                .salt_id(dto.getSaltId())
                .build();
        return memberAdmin;
    }
}
