package com.sjs.jsvill.service.jimmy.member;

import com.sjs.jsvill.dto.jimmy.member.MemberLoginDTO;
import com.sjs.jsvill.dto.jimmy.member.MemberUserDTO;
import com.sjs.jsvill.dto.jimmy.member.MemberUserLoginDTO;
import com.sjs.jsvill.entity.jimmy.MemberUser;

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
