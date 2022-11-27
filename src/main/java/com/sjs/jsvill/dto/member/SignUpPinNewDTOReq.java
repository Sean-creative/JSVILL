package com.sjs.jsvill.dto.member;

import com.sjs.jsvill.entity.enm.MemberRole;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import java.util.Collections;
import java.util.Set;


@Getter
@Setter
public class SignUpPinNewDTOReq {
    @Length(min = 4, max = 4, message = "핀번호는 숫자 4자리 입니다.")
    String pinNumber;

    @Length(min = 2, max = 32, message = "이름을 확인해주세요.")
    String name;

    @Email(message = "이메일 형식을 확인해주세요.")
    String email;

    String phoneNumber;
    boolean fromSocial = false;
    Set<MemberRole> roleSet = Collections.singleton(MemberRole.USER);
}