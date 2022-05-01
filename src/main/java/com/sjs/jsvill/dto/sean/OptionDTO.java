package com.sjs.jsvill.dto.sean;

import com.sjs.jsvill.entity.sean.Contract;
import com.sjs.jsvill.entity.sean.Option;
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
public class OptionDTO {
    private Long optionRowid;
    private List<String> optionList = new ArrayList<>();


    // [침대, 냉장고, TV] 라고 하면 -> String으로 "침대,냉장고,TV"로 분리해야함
    public static String listToCsv(List<String> optionList) {
        return String.join(",", optionList);
    }

    public static Option DTOToEntity(OptionDTO optionDTO, Long contractRowid) {
        Contract contarct = Contract.builder().contract_rowid(contractRowid).build();
        if(optionDTO==null) return null;
        else {
            Option option = Option.builder()
                    .contract(contarct)
                    .optionList(listToCsv(optionDTO.getOptionList()))
                    .build();
            return  option;
        }
    }
}
