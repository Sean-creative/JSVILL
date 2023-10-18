package com.sjs.jsvill.dto.view;

import com.sjs.jsvill.dto.PhotoDTO;
import com.sjs.jsvill.entity.Contract;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterPhotoResDTO {
    private String groupTitle;
    private String unitTitle;
    private Long contractRowid;
    private List<PhotoDTO> photoDTOList;
    private Boolean bookMark;


    public RegisterPhotoResDTO(Contract contract, List<PhotoDTO> photoDTOList) {
        this.unitTitle = contract.getUnit().getDetailaddr();
        this.groupTitle = contract.getUnit().getGroup().getTitle();
        this.contractRowid = contract.getContract_rowid();
        this.photoDTOList = photoDTOList;
    }
}