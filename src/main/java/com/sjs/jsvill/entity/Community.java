package com.sjs.jsvill.entity;

import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="community")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Community extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comRowid;

    @Column(name="type")
    private String type;

    @Column(name="title")
    private String title;

    @Column(name="cont")
    private String cont;

    @Column(name="readcnt")
    private int readCnt;

    @Column(name="writer")
    private String writer;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss") /* Todo kjs date format 변경할 수 있는 방법 찾기 */
    @Column(name="regdate", updatable = false, insertable = false) /* Todo kjs updatable, insetable 정확히 어떤 역할인지 알아보기 */
//    @Convert(converter= StringToFormatDateStringConverter.class)
    private LocalDateTime regdate;

    @Column(name="moddate", updatable = false, insertable = false)
    private LocalDateTime moddate;




}
