package com.sjs.jsvill.entity;

import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

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
    @Column(name="regdate", updatable = false, insertable = false) /* Todo kjs updatable, insetable 정확히 어떤 역할인지 알아보기 */
    private LocalDateTime regdate;

    @Column(name="moddate", updatable = false, insertable = false)
    private LocalDateTime moddate;




}
