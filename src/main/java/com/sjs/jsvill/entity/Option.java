package com.sjs.jsvill.entity;

import com.sjs.jsvill.dto.OptionDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "option")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"contract"})
public class Option extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long option_rowid;

    @ManyToOne
    @JoinColumn(name = "contract_rowid")
    private Contract contract;

    //이름은 List이지만 ,로 구분하는 string 값으로 들어가 있음
    @Column(length = 64, nullable = false)
    private String optionList;

    // 침대, 냉장고, TV 라고 하면 -> ,로 분리해서 배열로 줘야한다
    public List<String> csvToList(String csv) {
        String[] tokens = csv.split(",");
        return Arrays.asList(tokens);
    }


    //DB에서 해결이 안되기 때문에, 옵션을 list로 받아서, 그중 첫번째 값만 처리하기
    public static OptionDTO entityToDTO(Option option) {
        System.out.println("option : " + option);
        OptionDTO optionDTO;
        if(option!=null) {
            optionDTO = OptionDTO.builder()
                    .optionRowid(option.getOption_rowid())
                    .optionList(option.csvToList(option.getOptionList()))
                    .build();
        } else {
            optionDTO = OptionDTO.builder()
                    .optionList(new ArrayList<>())
                    .build();
        }
        return optionDTO;
    }
}