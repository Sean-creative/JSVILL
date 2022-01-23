package com.sjs.jsvill.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="memberadmin")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberAdmin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberadmin_rowid;

    @ManyToOne
    @JoinColumn(name = "member_rowid")
    private Member member_rowid;

    @Column(length = 64, nullable = false)
    private String userid;

    @Column(length = 64, nullable = false)
    private String pw;

    @ManyToOne
    @JoinColumn(name = "id")
    private _Salt salt_id;

}