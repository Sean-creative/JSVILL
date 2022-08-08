package com.sjs.jsvill.entity.sean;

import com.sjs.jsvill.dto.sean.OptionDTO;
import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "option_log")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"contract"})
public class OptionLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionLog_rowid;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "contract_rowid")
    private Contract contract;

    //이름은 List이지만 ,로 구분하는 string 값으로 들어가 있음
    @Column(length = 64, nullable = false)
    private String optionList;

    public void changeOptionList(List<String> optionList) {this.optionList = listToCsv(optionList);}

    public static OptionLog optionToOptionLog(Option option) {
        return  OptionLog.builder().contract(option.getContract()).optionList(option.getOptionList()).build();
    }


    // [침대, 냉장고, TV] 라고 하면 -> String으로 "침대,냉장고,TV"로 분리해야함
    // [침대, , TV] 라고 하면 -> "침대, TV"
    // [ , , ] 라고 하면 -> ""
    public static String listToCsv(List<String> optionList) {
        StringBuilder temp = new StringBuilder();
        optionList.forEach( s -> {
            if(s!=null && !s.isBlank()) {
                temp.append(s);
                temp.append(",");
            }
        });
        if(!temp.isEmpty()) temp.deleteCharAt(temp.length()-1);
        return temp.toString();
    }

    public static OptionLog DTOToEntity(OptionDTO optionDTO, Long contractRowid) {
        Contract contarct = Contract.builder().contract_rowid(contractRowid).build();
        if(optionDTO==null) return null;
        else {
            OptionLog option = OptionLog.builder()
                    .contract(contarct)
                    .optionList(listToCsv(optionDTO.getOptionList()))
                    .build();
            return  option;
        }
    }

}