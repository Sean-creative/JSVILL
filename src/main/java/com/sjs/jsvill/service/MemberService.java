package com.sjs.jsvill.service;

import com.sjs.jsvill.dto.MemberDTO;
import com.sjs.jsvill.entity.Member;

public interface MemberService {
    Long register(MemberDTO dto);

    //파라미터로 받는건 DTO인데 -> DB에 접근하는 데이터는 엔티티로 바꿔줘야함
    default Member dtoToEntity(MemberDTO dto) {
        Member member = Member.builder()
                ._memberType(dto.get_memberType_rowid()) //관리자or세입자
                .name(dto.getName()) //이름
                .build();
        return member;
    }

//    default MemberDTO entityToDTO(Member member) {
//        MemberDTO memberDTO = MemberDTO.builder()
//                .member_rowid(member.getMember_rowid())
//                .build();
//        return memberDTO;
//    }
}
