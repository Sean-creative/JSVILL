package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {

    //옵션은 계약이 등록될 때 빈값이라면 등록이 안될 수도 있다.
    Option findByContract(Contract contract);
}
