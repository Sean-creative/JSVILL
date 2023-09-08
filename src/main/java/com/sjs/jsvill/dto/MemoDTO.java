package com.sjs.jsvill.dto;

import com.sjs.jsvill.entity.Memo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoDTO {
    private Long unitRowid;
    private String content;

    public static List<MemoDTO> entityToDTOList(List<Memo> memoList) {
        List<MemoDTO> memoDTOList = new ArrayList<>();
        for (Memo memo : memoList) {
            memoDTOList.add(MemoDTO.builder()
                    .unitRowid(memo.getUnit().getUnit_rowid())
                    .content(memo.getContent()).build());
        }
        return memoDTOList;
    }
}
