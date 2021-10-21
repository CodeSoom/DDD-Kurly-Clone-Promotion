package com.kurly.coupon.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CouponPolicyTest {

    @DisplayName("쿠폰정책은 이름을 가진다.")
    @Test
    void coupon_name() {
        final Name GIVEN_NAME = Name.valueOf("test");
        final CouponPolicy couponPolicy = CouponPolicy.publishFixedAmountPolicy(GIVEN_NAME, new Keyword(), new Period(), new Amount(), new Count());

        assertThat(couponPolicy.getName()).isNotNull();
    }

    @DisplayName("쿠폰정책은 쿠폰 키워드를 가진다.")
    @Test
    void coupon_number() {
        final Keyword keyword = Keyword.valueOf("여름할인");
        final CouponPolicy couponPolicy = CouponPolicy.publishFixedAmountPolicy(new Name(), keyword, new Period(), new Amount(), new Count());
        assertThat(couponPolicy.getKeyword()).isNotNull();
    }

    @DisplayName("쿠폰정책은 시작일과 만료일의 기간을 가진다.")
    @Test
    void period() {
        final LocalDate startDate = LocalDate.of(2030, 1, 1);
        final LocalDate endDate = LocalDate.of(2030, 12, 31);
        final Period period = Period.createPeriod(startDate, endDate);

        final CouponPolicy couponPolicy = CouponPolicy.publishFixedAmountPolicy(new Name(), new Keyword(), period, new Amount(), new Count());

        assertAll(
                () -> assertThat(couponPolicy.getPeriod().getStartDate()).isNotNull(),
                () -> assertThat(couponPolicy.getPeriod().getEndDate()).isNotNull()
        );
    }

    @DisplayName("쿠폰정책은 정률 할인 정책을 가진다.")
    @Test
    void flat_policy() {
        CouponPolicy couponPolicy = CouponPolicy.publishRatePolicy(new Name(), new Keyword(), new Period(), Rate.valueOf(50), new Count());

        assertAll(
                () -> assertThat(couponPolicy).isInstanceOf(FlatRate.class),
                () -> assertThat(couponPolicy.getRate()).isEqualTo(50)
        );
    }

    @DisplayName("쿠폰정책은 정액 할인 정책을 가진다.")
    @Test
    void fix_policy() {
        final CouponPolicy couponPolicy = CouponPolicy.publishFixedAmountPolicy(new Name(), new Keyword(), new Period(), Amount.valueOf(500), new Count());
        assertAll(
                () -> assertThat(couponPolicy).isInstanceOf(FixedAmount.class),
                () -> assertThat(couponPolicy.getAmount()).isEqualTo(500)
        );
    }

    @DisplayName("쿠폰정책에 쿠폰을 생성할 개수를 지정할 수 있다.")
    @Test
    void count() {
        final CouponPolicy couponPolicy = CouponPolicy.publishFixedAmountPolicy(new Name(), new Keyword(), new Period(), new Amount(), Count.valueOf(5));

        assertThat(couponPolicy.getCount()).isEqualTo(5);
    }
}
