package com.sjs.jsvill.dto.view;

import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Tenant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterCarResDTO {
    private Long unitRowid;
    private String groupTitle;
    private String unitTitle;
    private Long contractRowid;
    List<Tenant> tenantList;

    //객체단위로 넘어올것 같아서, builder 패턴을 쓰지는 않겠음
    public RegisterCarResDTO(Contract contract, List<Tenant> tenantList) {
        this.unitRowid = contract.getUnit().getUnit_rowid();
        this.unitTitle = contract.getUnit().getDetailaddr();
        this.groupTitle = contract.getUnit().getGroup().getTitle();
        this.contractRowid = contract.getContract_rowid();
        this.tenantList = tenantList;
    }
}