package com.sjs.jsvill.entity;

import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "photo")
public class Photo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoRowid;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "contract_rowid")
    private Contract contract;

    //동일한 이름을 가진 파일이 업로드 되면 오류가 발생해서  -> 파일 원본명과 저장경로를 따로 지정함
    @Column(nullable = false)
    private String origFileName;  // 파일 원본명

    @Column(nullable = false, unique = true)
    private String fileKey;  // 파일 저장 경로

    @Column(nullable = false)
    private Boolean bookmark;

    @Column(nullable = false)
    private Long fileSize;

    @Transient
    private String fileUrl;

    @Builder
    public Photo(String origFileName, String fileKey, Long fileSize){
        this.origFileName = origFileName;
        this.fileKey = fileKey;
        this.fileSize = fileSize;
    }

    // Contract 정보 저장
    public void setContract(Contract contract){
        this.contract = contract;
    }
    public void setBookMark(Boolean bookmark){
        this.bookmark = bookmark;
    }
    public void setFileUrl(String fileUrl){
        this.fileUrl = fileUrl;
    }
}