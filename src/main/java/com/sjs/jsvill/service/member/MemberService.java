package com.sjs.jsvill.service.member;

import com.sjs.jsvill.dto.member.MemberDTO;
import com.sjs.jsvill.dto.member.SignUpPinNewDTOReq;
import com.sjs.jsvill.dto.member.SignUpPinOldDTOReq;
import com.sjs.jsvill.entity.Member;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findByPhoneNumber(String phoneNumber);

    void register(SignUpPinNewDTOReq req);
    void modify(SignUpPinOldDTOReq req);

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

    default Member dtoReqToEntity(SignUpPinNewDTOReq dto) {
        Member member = Member.builder()
                .phoneNumber(dto.getPhoneNumber())
                .pinNumber(dto.getPinNumber())
                .fromSocial(dto.isFromSocial())
                .name(dto.getName())
                .email(dto.getEmail())
                .roleSet(dto.getRoleSet())
                .build();
        return member;
    }

    default Member dtoReqToEntity(SignUpPinOldDTOReq dto) {
        Member member = Member.builder()
                .phoneNumber(dto.getPhoneNumber())
                .pinNumber(dto.getPinNumber())
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
