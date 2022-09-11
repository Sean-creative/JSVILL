package com.sjs.jsvill.entity;

import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="notice")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notice_rowid;

    @ManyToOne
    @JoinColumn(name = "memberRowid")
    private Member memberRowid;

    @Column(name = "title")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String contents;

    @Column(name = "viewcnt", columnDefinition = "integer default 0")
    private int viewcnt;

    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;


}