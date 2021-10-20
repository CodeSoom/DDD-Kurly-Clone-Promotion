package com.kurly.coupon.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CouponPolicyTest {
    private static final String GIVEN_NAME = "test";

    @DisplayName("쿠폰은 쿠폰이름을 가진다.")
    @Test
    void coupon_name() {
        final String couponNumber = UUID.randomUUID().toString();
        final CouponPolicy couponPolicy = CouponPolicy.createWithFixedPolicy(GIVEN_NAME, couponNumber, new Period(), 5, 500);

        assertThat(couponPolicy.getName()).isNotNull();
    }

    @DisplayName("쿠폰은 쿠폰번호를 가진다.")
    @Test
    void coupon_number() {
        final String couponNumber = UUID.randomUUID().toString();
        final CouponPolicy couponPolicy = CouponPolicy.createWithFixedPolicy(GIVEN_NAME, couponNumber, new Period(), 5, 500);
        assertThat(couponPolicy.getCouponNumber()).isNotNull();
    }

    @DisplayName("쿠폰은 시작일과 만료일의 기간을 가진다.")
    @Test
    void period() {
        final String couponNumber = UUID.randomUUID().toString();
        final LocalDate startDate = LocalDate.of(2030, 1, 1);
        final LocalDate endDate = LocalDate.of(2030, 12, 31);
        final Period period = Period.createPeriod(startDate, endDate);

        final CouponPolicy couponPolicy = CouponPolicy.createWithFixedPolicy(GIVEN_NAME, couponNumber, period, 5, 500);

        assertAll(
                () -> assertThat(couponPolicy.getPeriod().getStartDate()).isNotNull(),
                () -> assertThat(couponPolicy.getPeriod().getEndDate()).isNotNull()
        );
    }

    @DisplayName("쿠폰은 정률 할인 정책을 가진다.")
    @Test
    void flat_policy() {
        final String couponNumber = UUID.randomUUID().toString();
        final LocalDate startDate = LocalDate.of(2030, 1, 1);
        final LocalDate endDate = LocalDate.of(2030, 12, 31);
        final CouponPolicy couponPolicy = CouponPolicy.createWithRatePolicy(GIVEN_NAME, couponNumber, Period.createPeriod(startDate, endDate), 50, 5);

        assertAll(
                () -> assertThat(couponPolicy.getPolicy()).isEqualTo(Policy.FLAT),
                () -> assertThat(assertThat(couponPolicy.getRate()).isEqualTo(50))
        );
    }

    @DisplayName("쿠폰은 정액 할인 정책을 가진다.")
    @Test
    void fix_policy() {
        final String couponNumber = UUID.randomUUID().toString();
        final LocalDate startDate = LocalDate.of(2030, 1, 1);
        final LocalDate endDate = LocalDate.of(2030, 12, 31);
        final Period period = Period.createPeriod(startDate, endDate);
        final CouponPolicy couponPolicy = CouponPolicy.createWithFixedPolicy(GIVEN_NAME, couponNumber, period, 5, 500);

        assertThat(couponPolicy.getPolicy()).isEqualTo(Policy.FIXED);
    }

    @DisplayName("쿠폰을 만들 개수를 지정할 수 있다.")
    @Test
    void count() {
        final String couponNumber = UUID.randomUUID().toString();
        final LocalDate startDate = LocalDate.of(2030, 1, 1);
        final LocalDate endDate = LocalDate.of(2030, 12, 31);
        final Period period = Period.createPeriod(startDate, endDate);
        final CouponPolicy couponPolicy = CouponPolicy.createWithFixedPolicy(GIVEN_NAME, couponNumber, period, 5, 500);

        assertThat(couponPolicy.getCount()).isEqualTo(5);
    }
}
