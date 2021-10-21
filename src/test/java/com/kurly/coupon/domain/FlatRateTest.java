package com.kurly.coupon.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FlatRateTest {

    @Test
    void create() {
        final Name name = Name.of("test");
        final CouponNumber couponNumber = CouponNumber.of("aaa-bbb-ccc");
        final Period period = new Period();
        final Rate rate = Rate.valueOf(100);
        final Count count = Count.valueOf(100);
        FlatRate flatRate = new FlatRate(name, couponNumber, period, rate, count);

        assertThat(flatRate.getRate()).isNotNull();
    }
}
