package com.sjs.jsvill.entity;

import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="tenantpin")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TenantPin  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tenantpin_rowid;
}
