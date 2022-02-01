package com.sjs.jsvill.service.member;

import com.sjs.jsvill.dto.member.MemberAdminDTO;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity.MemberAdmin;
import com.sjs.jsvill.entity._Salt;

public interface MemberAdminService {
    Long register(MemberAdminDTO dto);

    default MemberAdmin dtoToEntity(MemberAdminDTO dto) {
        _Salt salt = _Salt.builder().id(dto.getSaltId()).build();
        Member member = Member.builder().member_rowid(dto.getMemberR()).build();
        MemberAdmin memberAdmin = MemberAdmin.builder()
                .member(member)
                .userid(dto.getUserId())
                .pw(dto.getPw())
                .salt(salt)
                .build();
        return memberAdmin;
    }
}
