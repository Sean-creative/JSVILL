package com.sjs.jsvill.dto.member;

import com.sjs.jsvill.entity.enm.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {
    private Long memberRowid;
    private String phoneNumber;
    @Length(min = 4, max = 4, message = "핀번호는 숫자 4자리 입니다.")
    private String pinNumber;
    private boolean fromSocial;
    private String name;
    private String email;
    private Set<MemberRole> roleSet;
}
