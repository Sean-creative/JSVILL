package com.sjs.jsvill.dto.view;

import com.sjs.jsvill.dto.PhotoDTO;
import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Memo;
import com.sjs.jsvill.entity.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterMemoResDTO {
    private String groupTitle;
    private String unitTitle;
    private Long unitRowid;
    private List<Memo> memoList;

    public RegisterMemoResDTO(Unit unit, List<Memo> memoList) {
        this.unitTitle = unit.getDetailaddr();
        this.groupTitle = unit.getGroup().getTitle();
        this.unitRowid = unit.getUnit_rowid();
        this.memoList = memoList;
    }
}