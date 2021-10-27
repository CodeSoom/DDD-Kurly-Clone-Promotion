package com.kurly.promotion.domain;

import com.kurly.promotion.domain.vo.Period;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountTest {
    private static final Long PRODUCT_ID = 1L;

    @DisplayName("할인을 상품에 적용할 수 있습니다.")
    @Test
    void registerTo_registers() {
        Discount discount = new Discount(new Period());

        discount.apply(PRODUCT_ID);

        assertThat(discount.getProductId()).isEqualTo(PRODUCT_ID);
    }
}
