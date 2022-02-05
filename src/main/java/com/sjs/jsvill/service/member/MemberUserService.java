package com.sjs.jsvill.service.member;

import com.sjs.jsvill.dto.member.MemberLoginDTO;
import com.sjs.jsvill.dto.member.MemberUserDTO;
import com.sjs.jsvill.dto.member.MemberUserLoginDTO;
import com.sjs.jsvill.entity.MemberUser;

public interface MemberUserService {
    Long register(MemberUserDTO dto);
    MemberUser get(String phone);
    MemberUserLoginDTO login(String phone, MemberLoginDTO memberUser);
    default MemberUser dtoToEntity(MemberUserDTO dto) {
        MemberUser memberUser = MemberUser.builder()
                .phone(dto.getPhone())
                .pin(dto.getPin())
                .build();
        return memberUser;
    }
    default MemberUserLoginDTO entityToDto(MemberUser entity)  {
        MemberUserLoginDTO memberUserLoginDTO = MemberUserLoginDTO.builder()
                .memberR(entity.getMemberuser_rowid())
                .phone(entity.getPhone())
                .build();
        return memberUserLoginDTO;
    }

}
