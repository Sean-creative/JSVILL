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

    @CreatedDate //@CreatedDate는 JPA에서 엔티티의 생성 시간을 처리한다.
    @Column(name = "regdate", updatable = false) //updatable=false를 통해서 해당 엔티티 객체를 데이터베이스에 반영할 때 regdate 칼럼값은 변경되지 않는다.
    private LocalDateTime regDate;


}