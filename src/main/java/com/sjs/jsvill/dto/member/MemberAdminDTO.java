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
public class MemberAdminDTO {
    private Long memberR;
    private String userId;
    private String pw;
    private String saltId;
}
