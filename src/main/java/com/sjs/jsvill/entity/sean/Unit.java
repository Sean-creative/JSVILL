package com.sjs.jsvill.entity.sean;

import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

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

    public void changeAddr2(String addr2) { this.detailaddr = addr2; }
    public void changeMemo(String memo) { this.memo = memo; }

    //단순히 호실만 다룰 때

}