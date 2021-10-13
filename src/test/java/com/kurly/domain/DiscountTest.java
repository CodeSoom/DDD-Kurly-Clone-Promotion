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

    @DisplayName("attach는 할인 대상 상품을 정합니다.")
    @Test
    void registerTo_registers() {
        Discount discount = Discount.createFlatRateDiscount(10);

        discount.attach(PRODUCT_ID);

        assertThat(discount.getProductId()).isEqualTo(PRODUCT_ID);
    }
}
