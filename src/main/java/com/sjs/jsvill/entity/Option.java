package com.sjs.jsvill.entity;

import com.sjs.jsvill.dto.OptionDTO;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="option")
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

    public static OptionDTO entityToDTO(Option option) {
        OptionDTO optionDTO = new OptionDTO();
        if (option != null) {
            optionDTO.setOptionRowid(option.getOption_rowid());
            optionDTO.setOptionList(option.csvToList(option.getOptionList()));
        }
        return optionDTO;
    }
}