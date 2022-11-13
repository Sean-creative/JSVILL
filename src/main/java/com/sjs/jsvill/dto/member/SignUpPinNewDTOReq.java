package com.sjs.jsvill.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignUpPinNewDTOReq {
    @NotEmpty
    @Length(min = 4, max = 4, message = "핀번호는 숫자 4자리 입니다.")
    String pinNumber;
}