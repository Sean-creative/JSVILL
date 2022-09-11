package com.sjs.jsvill.service.member;

import com.sjs.jsvill.dto.member.MemberDTO;
import com.sjs.jsvill.entity.Member;

public interface MemberService {

    Member findByPhoneNumber(String phoneNumber);

    Long register(MemberDTO dto);

    //파라미터로 받는건 DTO인데 -> DB에 접근하는 데이터는 엔티티로 바꿔줘야함
    default Member dtoToEntity(MemberDTO dto) {
        Member member = Member.builder()
                .memberRowid(dto.getMemberRowid())
                .phoneNumber(dto.getPhoneNumber())
                .pinNumber(dto.getPinNumber())
                .fromSocial(dto.isFromSocial())
                .name(dto.getName())
                .email(dto.getEmail())
                .roleSet(dto.getRoleSet())
                .build();
        return member;
    }

//    default MemberDTO entityToDTO(Member member) {
//        MemberDTO memberDTO = MemberDTO.builder()
//                .memberRowid(member.getMemberRowid())
//                .build();
//        return memberDTO;
//    }
}
