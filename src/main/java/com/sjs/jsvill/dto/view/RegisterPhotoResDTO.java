package com.sjs.jsvill.dto.view;

import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Tenant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterPhotoResDTO {
    private String groupTitle;
    private String unitTitle;
    private Long contractRowid;


    public RegisterPhotoResDTO(Contract contract) {
        this.unitTitle = contract.getUnit().getDetailaddr();
        this.groupTitle = contract.getUnit().getGroup().getTitle();
        this.contractRowid = contract.getContract_rowid();
    }
}