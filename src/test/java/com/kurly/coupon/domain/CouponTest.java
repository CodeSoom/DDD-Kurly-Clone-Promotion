package com.kurly.coupon.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CouponTest {

    @DisplayName("쿠폰은 쿠폰번호를 가진다.")
    @Test
    void coupon_number() {
        final String couponNumber = UUID.randomUUID().toString();
        final Coupon coupon = Coupon.createWithFixPolicy(couponNumber, new Period());

        assertThat(coupon.getCouponNumber()).isNotNull();
    }

    @DisplayName("쿠폰은 시작일과 만료일의 기간을 가진다.")
    @Test
    void period() {
        final String couponNumber = UUID.randomUUID().toString();
        final LocalDate startDate = LocalDate.of(2030, 1, 1);
        final LocalDate endDate = LocalDate.of(2030, 12, 31);
        final Period period = Period.createPeriod(startDate, endDate);

        final Coupon coupon = Coupon.createWithFixPolicy(couponNumber, period);

        assertAll(
                () -> assertThat(coupon.getPeriod().getStartDate()).isNotNull(),
                () -> assertThat(coupon.getPeriod().getEndDate()).isNotNull()
        );

    }

    @DisplayName("쿠폰은 정률 할인 정책을 가진다.")
    @Test
    void flat_policy() {
        final String couponNumber = UUID.randomUUID().toString();
        final Coupon coupon = Coupon.createWithFlatPolicy(couponNumber, new Period());

        assertThat(coupon.getPolicy()).isEqualTo(Policy.FLAT);
    }

    @DisplayName("쿠폰은 정액 할인 정책을 가진다.")
    @Test
    void fix_policy() {
        final String couponNumber = UUID.randomUUID().toString();
        final Coupon coupon = Coupon.createWithFixPolicy(couponNumber, new Period());

        assertThat(coupon.getPolicy()).isEqualTo(Policy.FIX);
    }

}
