package com.sjs.jsvill.service.member;

import com.sjs.jsvill.dto.member.MemberAdminDTO;
import com.sjs.jsvill.dto.member.MemberLoginDTO;
import com.sjs.jsvill.dto.member.MemberUserDTO;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity.MemberAdmin;
import com.sjs.jsvill.entity.MemberUser;

public interface MemberUserService {
    Long register(MemberUserDTO dto);
    MemberUser get(String phone);
    MemberUser login(String phone, MemberLoginDTO memberUser);
    default MemberUser dtoToEntity(MemberUserDTO dto) {
        MemberUser memberUser = MemberUser.builder()
                .phone(dto.getPhone())
                .pin(dto.getPin())
                .build();
        return memberUser;
    }
}
