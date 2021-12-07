package com.sjs.jsvill.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.Column;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {

    private int page;
    private int size;
    private String type;
    private String keyword;

    //기본 값을 가진다.
    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }
    //JPA의 경우 페이지 번호가 0부터 시작한다는점
    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page -1, size, sort);
    }
}
