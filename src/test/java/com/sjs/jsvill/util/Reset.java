package com.sjs.jsvill.util;


import com.sjs.jsvill.entity.sub.*;
import com.sjs.jsvill.repository.MemberRepository;
import com.sjs.jsvill.repository.sub.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class Reset {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    _PostTypeRepository postTypeRepository;
    @Autowired
    _MemberTypeRepository memberTypeRepository;
    @Autowired
    _LivingTypeRepository livingTypeRepository;
    @Autowired
    _GroupTypeRepository groupTypeRepository;
    @Autowired
    _ContractTypeRepository contractTypeRepository;

    @Test
    public void reset() {
        List<_PostType> postTypeList = new ArrayList<>(Arrays.asList(
                _PostType.builder()._posttype_rowid(10L).title("공지사항").build(),
                _PostType.builder()._posttype_rowid(20L).title("자유게시판").build()
        ));
        List<_MemberType> memberTypeList = new ArrayList<>(Arrays.asList(
                _MemberType.builder()._membertype_rowid(10L).title("집주인").build(),
                _MemberType.builder()._membertype_rowid(20L).title("세입자").build()
        ));
        List<_LivingType> livingTypeList = new ArrayList<>(Arrays.asList(
                _LivingType.builder()._livingtype_rowid(10L).title("거주X").build(),
                _LivingType.builder()._livingtype_rowid(20L).title("거주O").build(),
                _LivingType.builder()._livingtype_rowid(30L).title("거주 예정").build()
        ));
        List<_GroupType> groupTypeList = new ArrayList<>(Arrays.asList(
                _GroupType.builder()._grouptype_rowid(10L).title("전세").build(),
                _GroupType.builder()._grouptype_rowid(20L).title("반전세").build(),
                _GroupType.builder()._grouptype_rowid(30L).title("월세").build()
        ));
        List<_ContractType> contractTypeList = new ArrayList<>(Arrays.asList(
                _ContractType.builder()._contracttype_rowid(10L).title("전세").build(),
                _ContractType.builder()._contracttype_rowid(20L).title("반전세").build(),
                _ContractType.builder()._contracttype_rowid(30L).title("월세").build()
        ));
        postTypeRepository.saveAll(postTypeList);
        memberTypeRepository.saveAll(memberTypeList);
        livingTypeRepository.saveAll(livingTypeList);
        groupTypeRepository.saveAll(groupTypeList);
        contractTypeRepository.saveAll(contractTypeList);
    }
}
