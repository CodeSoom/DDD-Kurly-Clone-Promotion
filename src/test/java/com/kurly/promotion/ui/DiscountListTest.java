package com.kurly.promotion.ui;

import com.kurly.promotion.domain.Discount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountListTest {

    @DisplayName("list")
    @Nested
    class Describe_list {

        @DisplayName("할인 엔티티를 DTO객체로 변환한 목록을 반환합니다")
        @Test
        void it_returns_discount_entity_list() {
            List<Discount> discountList = List.of(
                    new Discount(1L, 10, 1004L)
            );

            List<DiscountData> list = new DiscountList(discountList).list();

            assertThat(list.get(0).getId())
                    .isEqualTo(discountList.get(0).getId());
            assertThat(list.get(0).getFlatRate())
                    .isEqualTo(discountList.get(0).getFlatRate());
            assertThat(list.get(0).getProductId())
                    .isEqualTo(discountList.get(0).getProductId());
        }
    }
}
