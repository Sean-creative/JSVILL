package com.sjs.jsvill.dto.jimmy.notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NoticeResDTO {
    private String writer;
    private String title;
    private String contents;
    private int viewCnt;
    private String regDate;
}
