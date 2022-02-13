package com.sjs.jsvill.dto;

import com.sjs.jsvill.entity._MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {
    private _MemberType _memberTypeRowid;
    private String name;
}
