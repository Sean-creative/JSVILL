package com.sjs.jsvill.dto.member;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@Getter
@Setter
public class SignUpPinOldDTOReq {
    @Length(min = 4, max = 4, message = "핀번호는 숫자 4자리 입니다.")
    String pinNumber;

    String phoneNumber;
}