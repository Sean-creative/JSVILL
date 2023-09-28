package com.sjs.jsvill.entity;

import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="board")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_rowid;

    @Column(columnDefinition = "TEXT")
    private String contents;

    @Column
    private String isdeleted; //삭제됨 1 삭제 안됨 0

    @ManyToOne
    @JoinColumn(name = "memberRowid")
    private Member memberRowid;
}