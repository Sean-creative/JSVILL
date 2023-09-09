package com.sjs.jsvill.entity;

import com.sjs.jsvill.dto.MemoDTO;
import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "memo")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"contract"})
public class Memo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memoRowid;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "unit_rowid")
    private Unit unit;

    @Column(nullable = false)
    private String content;

    public static Memo dtoToEntity(MemoDTO memoDTO) {
        Unit unit = Unit.builder().unit_rowid(memoDTO.getUnitRowid()).build();
        return Memo.builder().unit(unit).content(memoDTO.getContent()).build();
    }
}