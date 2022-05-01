package com.sjs.jsvill.entity.sub;

import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="_rsakey")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class _RsaKey extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _rsakey_rowid;

    @Column(length = 5, nullable = false)
    private char id;

    @Column(length = 100, nullable = false)
    private String publickey;

    @Column(length = 100, nullable = false)
    private String privatekey;
}