package com.sjs.jsvill.entity;

import com.sjs.jsvill.dto.UnitDTO;
import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="unit")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "group")
public class Unit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unit_rowid;

//    @ManyToOne( fetch = FetchType.LAZY)
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "group_rowid")
    private Group group;

    @Column(length = 64, nullable = false)
    private String detailaddr; // 호수

    @Column(nullable = false)
    private String memo; // 메모 (선택적)

    public static UnitDTO entityToDTO(Unit unit) {
        return UnitDTO.builder()
                .unitRowid(unit.getUnit_rowid())
                .groupRowid(unit.getGroup().getGroup_rowid())
                .detailAddr(unit.getDetailaddr().replace("호", ""))
                .memo(unit.getMemo())
//                .groupTitle(unit.group.getTitle())
//                .groupAddr(unit.group.getLandaddr())
                .build();
    }


    public void changedetailAddr(String detailAddr) { this.detailaddr = detailAddr; }
    public void changeMemo(String memo) { this.memo = memo; }
}