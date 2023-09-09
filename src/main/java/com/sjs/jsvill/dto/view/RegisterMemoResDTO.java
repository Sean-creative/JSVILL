package com.sjs.jsvill.dto.view;

import com.sjs.jsvill.dto.MemoDTO;
import com.sjs.jsvill.entity.Memo;
import com.sjs.jsvill.entity.Unit;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterMemoResDTO {
    private String groupTitle;
    private String unitTitle;
    private Long unitRowid;
    @Builder.Default
    private List<MemoDTO> memoDTOList = new ArrayList<>();

    public RegisterMemoResDTO(Unit unit, List<Memo> memoList) {
        this.unitTitle = unit.getDetailaddr();
        this.groupTitle = unit.getGroup().getTitle();
        this.unitRowid = unit.getUnit_rowid();
        this.memoDTOList =  MemoDTO.entityToDTOList(memoList);
    }
}