package com.sjs.jsvill.dto;

import com.sjs.jsvill.entity.Memo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoDTO {
    private Long memoRowid;
    private Long unitRowid;
    private String content;
    private String regDate;

    public static List<MemoDTO> entityToDTOList(List<Memo> memoList) {
        List<MemoDTO> memoDTOList = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm yy/MM/dd");
        for (Memo memo : memoList) {
            memoDTOList.add(MemoDTO.builder()
                    .memoRowid(memo.getMemoRowid())
                    .unitRowid(memo.getUnit().getUnit_rowid())
                    .content(memo.getContent())
                    .regDate(memo.getRegDate().format(formatter)).build());
        }
        return memoDTOList;
    }
}

