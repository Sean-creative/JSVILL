package com.sjs.jsvill.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class QueryDslConfig {

    //JPAQueryFactory는 QueryDSL이 제공하는 JPA를 사용하여 데이터를 조회할 때 사용하는 클래스다.
    //JPAQueryFactory를 사용하면 복잡한 쿼리를 type-safe하게 작성할 수 있다.
    //즉, 쿼리를 작성하는 도중에 발생할 수 있는 오타나 타입 미스매치 등의 문제를 컴파일 타임에 잡아낼 수 있다.
    @Bean
    //Bean으로 등록해서 여러 군데에서 사용할 수 있도록 해준다.
    public JPAQueryFactory jpaQueryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }
}