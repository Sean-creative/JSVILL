package com.sjs.jsvill.entity;

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

    @OneToMany(
            mappedBy = "unit",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Photo> photo = new ArrayList<>();

    public void changedetailAddr(String detailAddr) { this.detailaddr = detailAddr; }
    public void changeMemo(String memo) { this.memo = memo; }

    // 호실에서 파일 처리 위함
    public void addPhoto(Photo photo) {
        this.photo.add(photo);

        // 호실에 파일이 저장되어있지 않은 경우
        if(photo.getUnit() != this)
            // 파일 저장
            photo.setUnit(this);
    }
}