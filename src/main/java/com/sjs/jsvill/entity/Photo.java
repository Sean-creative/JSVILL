package com.sjs.jsvill.entity;

import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "file")
public class Photo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long file_rowid;

    @ManyToOne
    @JoinColumn(name = "unit_rowid")
    private Unit unit;

    @Column(nullable = false)
    private String origFileName;  // 파일 원본명

    @Column(nullable = false)
    private String filePath;  // 파일 저장 경로

    private Long fileSize;

    @Builder
    public Photo(String origFileName, String filePath, Long fileSize){
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    // Unit 정보 저장
    public void setUnit(Unit unit){
        this.unit = unit;
        // 게시글에 현재 파일이 존재하지 않는다면
        if(!unit.getPhoto().contains(this))
            // 파일 추가
            unit.getPhoto().add(this);
    }
}