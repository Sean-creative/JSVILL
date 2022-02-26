package com.sjs.jsvill.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardResDTO {
    private String writer;
    private boolean isAdmin;
    private String contents;
    private String regDate;
    private boolean isDeleted;
}
