package com.sjs.jsvill.dto;

import com.sjs.jsvill.entity.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OptionDTO {
    private Long optionRowid;
    private Long contractRowid;
    @Builder.Default
    private List<String> optionList = new ArrayList<>();


    // 침대, 냉장고, TV 라고 하면 -> ,로 분리해서 배열로 줘야한다
    public static List<String> csvToList(String csv) {
        String[] tokens = csv.split(",");
        return Arrays.asList(tokens);
    }

    //DB에서 해결이 안되기 때문에, 옵션을 list로 받아서, 그중 첫번째 값만 처리하기
    public static OptionDTO entityToDTO(Option option) {
        OptionDTO optionDTO = new OptionDTO();
        if(option!=null) {
            optionDTO = OptionDTO.builder()
                    .optionRowid(option.getOption_rowid())
                    .contractRowid(option.getContract().getContract_rowid())
                    .optionList(csvToList(option.getOptionList()))
                    .build();
        }
        return optionDTO;
    }
}
