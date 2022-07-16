package com.sjs.jsvill.dto.sean.view;

import com.sjs.jsvill.dto.sean.CarDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterCarReqDTO {
    private Long unitRowid;
    private List<CarDTO> carDTOList;

}