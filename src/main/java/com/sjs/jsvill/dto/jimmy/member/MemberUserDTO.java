package com.sjs.jsvill.dto.jimmy.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberUserDTO {
    private Long memberR;
    private String phone;
    private String pin;
}
