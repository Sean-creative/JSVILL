package com.sjs.jsvill.service;

import com.sjs.jsvill.dto.MemberDTO;
import com.sjs.jsvill.entity.Member;

public interface MemberService {
    Long register(MemberDTO dto);

    //파라미터로 받는건 DTO인데 -> DB에 접근하는 데이터는 엔티티로 바꿔줘야함
    default Member dtoToEntity(MemberDTO dto) {
        Member member = Member.builder()
                .member_rowid(dto.getMember_rowid())
                .userid(dto.getUserid())
                .email(dto.getEmail())
                .pw(dto.getPw())
                .username(dto.getUsername())
                .build();
        return member;
    }

    default MemberDTO entityToDTO(Member member) {
        MemberDTO memberDTO = MemberDTO.builder()
                .member_rowid(member.getMember_rowid())
                .userid(member.getUserid())
                .email(member.getEmail())
                .pw(member.getPw())
                .username(member.getUsername())
                .build();
        return memberDTO;
    }
}
