package com.sjs.jsvill.entity.sean;

import com.sjs.jsvill.entity.common.BaseEntity;
import com.sjs.jsvill.entity.sub._GroupType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="`group`")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "_grouptype")
public class Group extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long group_rowid;

    @ManyToOne
    @JoinColumn(name = "_grouptype_rowid", nullable = false)
    private _GroupType _grouptype;

    @Column(length = 64, nullable = false)
    private String title;

    @Column(nullable = false)
    private String addr1;

    @Column(length = 10, nullable = false)
    private String postNum;

    @Column(nullable = false)
    private String memo;

    @Column(length = 32, nullable = false)
    private String completiondate;

    public void changeTitle(String title){
        this.title = title;
    }
    public void changeAddr1(String addr1){
        this.addr1 = addr1;
    }
    public void changePostNum(String postNum){
        this.postNum = postNum;
    }
    public void changeMemo(String memo){
        this.memo = memo;
    }
    public void changeCompletionDate(String completiondate){
        this.completiondate = completiondate;
    }
}