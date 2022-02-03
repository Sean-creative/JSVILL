package com.sjs.jsvill.dto;

import com.sjs.jsvill.entity.Contract;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OptionDTO {
    private Long option_rowid;
    private Contract contract;
    private String title;
}
