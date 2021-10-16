package com.kurly.promotion.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountTest {
    static final Long PRODUCT_ID = 1L;

    @DisplayName("createFlatRateDiscount는 정률 할인을 만든다.")
    @Test
    void createFlatRateDiscount_creates_fixRateDiscount() {
        Discount discount = Discount.createFlatRateDiscount(10);

        assertThat(discount.getFlatRate()).isEqualTo(10);
    }

    @DisplayName("할인을 상품에 적용할 수 있습니다.")
    @Test
    void registerTo_registers() {
        Discount discount = Discount.createFlatRateDiscount(10);

        discount.apply(PRODUCT_ID);

        assertThat(discount.getProductId()).isEqualTo(PRODUCT_ID);
    }
}
