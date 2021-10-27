package com.kurly.promotion.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DiscountFactoryTest {

    private DiscountFactory discountFactory;

    private DiscountCommand.RegisterDiscount requestDiscount;

    @BeforeEach
    void setUp() {
        discountFactory = new DiscountFactory();
        requestDiscount = DiscountCommand.RegisterDiscount.builder()
                .startDate(LocalDate.of(2020, 10, 25))
                .endDate(LocalDate.of(2230, 11, 25))
                .flatRate(20)
                .fixedAmout(20)
                .build();
    }

    @DisplayName("DiscountType이 FLAT_RATE일 경우 FlatRateDiscount 인스턴스를 반환한다.")
    @Test
    void create_flatRateDiscount() {
        Discount discount = discountFactory.createDiscount(requestDiscount, DiscountType.FLAT_RATE);

        assertThat(discount).isInstanceOf(FlatRateDiscount.class);
    }

    @DisplayName("DiscountType이 FIXED_AMOUNT일 경우 FixedAmountDiscount 인스턴스를 반환한다.")
    @Test
    void create_fixedAmountDiscount() {
        Discount discount = discountFactory.createDiscount(requestDiscount, DiscountType.FIXED_AMOUNT);

        assertThat(discount).isInstanceOf(FixedAmountDiscount.class);
    }
}
