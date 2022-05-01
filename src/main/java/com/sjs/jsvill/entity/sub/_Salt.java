package com.sjs.jsvill.entity.sub;

import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="_salt")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class _Salt extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _salt_rowid;

    @Column(length = 5, nullable = false)
    private String id;

    @Column(length = 255, nullable = false)
    private String salt;
}