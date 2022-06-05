package com.sjs.jsvill.dto.sean.view;

import com.sjs.jsvill.entity.sean.Contract;
import com.sjs.jsvill.entity.sean.Tenant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterCarDTO {
    private String groupTitle;
    private String unitTitle;
    private Long contractRowid;
    List<Tenant> tenantList;

    //객체단위로 넘어올것 같아서, builder 패턴을 쓰지는 않겠음
    public RegisterCarDTO(Contract contract, List<Tenant> tenantList) {
        this.unitTitle = contract.getUnit().getAddr2();
        this.groupTitle = contract.getUnit().getGroup().getTitle();
        this.contractRowid = contract.getContract_rowid();
        this.tenantList = tenantList;
    }
}