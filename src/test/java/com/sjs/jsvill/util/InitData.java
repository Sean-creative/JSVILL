package com.sjs.jsvill.util;


import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity.enm.MemberRole;
import com.sjs.jsvill.entity.defaultType.*;
import com.sjs.jsvill.repository.MemberRepository;
import com.sjs.jsvill.repository.sub.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SpringBootTest
//DB 초기화시 기본값 넣어주는 테스트 클래스
public class InitData {
    @Autowired
    _ContractTypeRepository contractTypeRepository;
    @Autowired
    _GroupTypeRepository groupTypeRepository;
    @Autowired
    _LivingTypeRepository livingTypeRepository;
    @Autowired
    _MemberTypeRepository memberTypeRepository;
    @Autowired
    _PostTypeRepository postTypeRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void initData() {
        List<_ContractType> contractTypeList = new ArrayList<>(Arrays.asList(
                _ContractType.builder()._contracttype_rowid(10L).title("전세").build(),
                _ContractType.builder()._contracttype_rowid(20L).title("반전세").build(),
                _ContractType.builder()._contracttype_rowid(30L).title("월세").build(),
                _ContractType.builder()._contracttype_rowid(40L).title("자가").build()
        ));
        List<_GroupType> groupTypeList = new ArrayList<>(Arrays.asList(
                _GroupType.builder()._grouptype_rowid(10L).title("전세").build(),
                _GroupType.builder()._grouptype_rowid(20L).title("반전세").build(),
                _GroupType.builder()._grouptype_rowid(30L).title("월세").build()
        ));
        List<_LivingType> livingTypeList = new ArrayList<>(Arrays.asList(
                _LivingType.builder()._livingtype_rowid(10L).title("거주X").build(),
                _LivingType.builder()._livingtype_rowid(20L).title("거주O").build(),
                _LivingType.builder()._livingtype_rowid(30L).title("거주 예정").build()
        ));
        List<_MemberType> memberTypeList = new ArrayList<>(Arrays.asList(
                _MemberType.builder()._membertype_rowid(10L).title("집주인").build(),
                _MemberType.builder()._membertype_rowid(20L).title("세입자").build()
        ));
        List<_PostType> postTypeList = new ArrayList<>(Arrays.asList(
                _PostType.builder()._posttype_rowid(10L).title("공지사항").build(),
                _PostType.builder()._posttype_rowid(20L).title("자유게시판").build()
        ));
        Member member = Member.builder()
                .phoneNumber("010-0000-0000")
                .pinNumber("$2a$10$twyZJK/gTETzD95OLF2rHeBqyHAGH9jws1WI1rcAY8d4BQdf73mhO")
                .fromSocial(false)
                .name("TEST")
                .email("test@test.com")
                .roleSet(Set.of(MemberRole.USER, MemberRole.ADMIN))
        .build();

        contractTypeRepository.saveAll(contractTypeList);
        groupTypeRepository.saveAll(groupTypeList);
        livingTypeRepository.saveAll(livingTypeList);
        memberTypeRepository.saveAll(memberTypeList);
        postTypeRepository.saveAll(postTypeList);
        memberRepository.save(member);
    }
}
