package com.sjs.jsvill.dto.jimmy.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberLoginDTO {
    private String phone;
    private String pin;
}
