package com.kurly.coupon.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FixedAmountTest {

    @Test
    void create() {
        final Name name = Name.of("test");
        final CouponCode couponCode = CouponCode.of("aaa-bbb-ccc");
        final Period period = new Period();
        final Amount amount = Amount.valueOf(100);
        final Count count = Count.valueOf(100);
        FixedAmount fixedAmount = new FixedAmount(name, couponCode, period, amount, count);

        assertThat(fixedAmount.getAmount()).isNotNull();
    }
}
