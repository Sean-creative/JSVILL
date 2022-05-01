package com.sjs.jsvill.entity.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass //@MappedSuperclass라는 특별한 어노테이션은 적용된 클래스는 테이블로 생성되지 않는다.
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public abstract class BaseEntity {

    @CreatedDate //@CreatedDate는 JPA에서 엔티티의 생성 시간을 처리한다.
    @Column(name = "regdate", updatable = false) //updatable=false를 통해서 해당 엔티티 객체를 데이터베이스에 반영할 때 regdate 칼럼값은 변경되지 않는다.
    protected LocalDateTime regDate;

    @LastModifiedDate //@LastModifiedDate는 최종 수정 시간을 자동으로 처리하는 용도로 사용한다.
    @Column(name = "moddate")
    protected LocalDateTime modDate;
}
