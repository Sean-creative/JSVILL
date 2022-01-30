package com.sjs.jsvill.dto.member;

import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity._Salt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberUserDTO {
    private Member memberR;
    private String phone;
    private String pin;
}
