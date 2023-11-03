package com.sjs.jsvill.controller.util;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import org.springframework.data.domain.Sort;

public class QueryDslUtil {

    public static <T extends Comparable<? super T>> OrderSpecifier<T> getOrderSpecifier(Sort sortOrder, ComparableExpressionBase<T> expression) {
        if (sortOrder.isSorted()) {
            Sort.Order order = sortOrder.iterator().next(); // 첫 번째 정렬만 고려

            if (order.isAscending()) {
                return new OrderSpecifier<>(Order.ASC, expression);
            } else {
                return new OrderSpecifier<>(Order.DESC, expression);
            }
        }

        // 기본 정렬은 ascending으로 설정
        return new OrderSpecifier<>(Order.ASC, expression);
    }
}