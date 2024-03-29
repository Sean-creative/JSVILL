package com.sjs.jsvill.entity;

import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="community")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Community extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comRowid;

    @Column
    private String type;

    @Column
    private String title;

    @Column
    private String cont;

    @Column
    private int readCnt;

    @Column
    private String writer;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd") /* Todo kjs date format 변경할 수 있는 방법 찾기 */
    @Column(updatable = false, insertable = false) /* Todo kjs updatable, insetable 정확히 어떤 역할인지 알아보기 */
//    @Convert(converter= StringToFormatDateStringConverter.class)
    private LocalDateTime regdate;

    @Column(updatable = false, insertable = false)
    private LocalDateTime moddate;




}
