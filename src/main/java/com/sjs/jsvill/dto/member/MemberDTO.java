package com.sjs.jsvill.dto.member;

import com.sjs.jsvill.entity.enm.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {
    private Long memberRowid;
    private String phoneNumber;
    private String pinNumber;
    private boolean fromSocial;
    private String name;
    private String email;
    private Set<MemberRole> roleSet;
}
