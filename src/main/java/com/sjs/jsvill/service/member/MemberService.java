package com.sjs.jsvill.service.member;

import com.sjs.jsvill.dto.MemberDTO;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity._MemberType;

public interface MemberService {
    Long register(MemberDTO dto);

    //파라미터로 받는건 DTO인데 -> DB에 접근하는 데이터는 엔티티로 바꿔줘야함
    default Member dtoToEntity(MemberDTO dto) {
        _MemberType mt = _MemberType.builder()._membertype_rowid(dto.get_memberType_rowid()).build();
        Member member = Member.builder()
                ._memberType(mt) //관리자or세입자
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
