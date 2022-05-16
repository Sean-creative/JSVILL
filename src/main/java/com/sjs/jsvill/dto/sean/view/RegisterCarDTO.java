package com.sjs.jsvill.dto.sean.view;

import com.sjs.jsvill.entity.sean.Contract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterCarDTO {
    private String groupTitle;
    private String unitTitle;

    public RegisterCarDTO(Contract contract) {
        this.unitTitle = contract.getUnit().getAddr2();
        this.groupTitle = contract.getUnit().getGroup().getTitle();
    }
}
