package com.sjs.jsvill.repository.unit;

import com.sjs.jsvill.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    // 인터페이스 선언만으로도 자동으로 스프링의 빈(bean)으로 등록된다.
    // -> 스프링이 내부적으로 인터페이스 타입에 맞는 객체를 생성해서 빈으로 등록한다.
}
