package com.sjs.jsvill.dto;

import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommunityDTO {

    private Long comRowid;
    private String type;
    private String title;
    private String cont;
    private int readCnt;
    private String writer;
    private LocalDateTime regdate;
    private LocalDateTime moddate;

}
