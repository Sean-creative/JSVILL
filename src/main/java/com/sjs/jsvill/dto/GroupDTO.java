package com.sjs.jsvill.dto;

import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity._GroupType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroupDTO {
    private Long group_rowid;
    private Member member;
    private _GroupType groupType;
    private String title;
    private String addr1;
    private String postnum;
    private String memo;
}
