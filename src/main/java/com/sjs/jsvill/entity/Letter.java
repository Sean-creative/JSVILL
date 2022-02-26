package com.sjs.jsvill.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="letter")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Letter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long letter_rowid;

    @Column(columnDefinition = "TEXT")
    private String contents;

    @Column(length = 4, nullable = false)
    private int checked;

}