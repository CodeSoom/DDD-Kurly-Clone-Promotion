package com.kurly.coupon.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CouponPolicyTest {

    @DisplayName("쿠폰은 쿠폰이름을 가진다.")
    @Test
    void coupon_name() {
        final Name GIVEN_NAME = Name.valueOf("test");
        final CouponPolicy couponPolicy = CouponPolicy.createWithFixedPolicy(GIVEN_NAME, new Keyword(), new Period(), new Amount(), new Count());

        assertThat(couponPolicy.getName()).isNotNull();
    }

    @DisplayName("쿠폰은 쿠폰 키워드를 가진다.")
    @Test
    void coupon_number() {
        final Keyword keyword = Keyword.valueOf("여름할인");
        final CouponPolicy couponPolicy = CouponPolicy.createWithFixedPolicy(new Name(), keyword, new Period(), new Amount(), new Count());
        assertThat(couponPolicy.getKeyword()).isNotNull();
    }

    @DisplayName("쿠폰은 시작일과 만료일의 기간을 가진다.")
    @Test
    void period() {
        final LocalDate startDate = LocalDate.of(2030, 1, 1);
        final LocalDate endDate = LocalDate.of(2030, 12, 31);
        final Period period = Period.createPeriod(startDate, endDate);

        final CouponPolicy couponPolicy = CouponPolicy.createWithFixedPolicy(new Name(), new Keyword(), period, new Amount(), new Count());

        assertAll(
                () -> assertThat(couponPolicy.getPeriod().getStartDate()).isNotNull(),
                () -> assertThat(couponPolicy.getPeriod().getEndDate()).isNotNull()
        );
    }

    @DisplayName("쿠폰은 정률 할인 정책을 가진다.")
    @Test
    void flat_policy() {
        CouponPolicy couponPolicy = CouponPolicy.createWithRatePolicy(new Name(), new Keyword(), new Period(), Rate.valueOf(50), new Count());

        assertAll(
                () -> assertThat(couponPolicy.getPolicy()).isEqualTo(Policy.FLAT_RATE),
                () -> assertThat(couponPolicy.getRate()).isEqualTo(50)
        );
    }

    @DisplayName("쿠폰은 정액 할인 정책을 가진다.")
    @Test
    void fix_policy() {
        final CouponPolicy couponPolicy = CouponPolicy.createWithFixedPolicy(new Name(), new Keyword(), new Period(), new Amount(), new Count());

        assertThat(couponPolicy.getPolicy()).isEqualTo(Policy.FIXED_AMOUNT);
    }

    @DisplayName("쿠폰을 만들 개수를 지정할 수 있다.")
    @Test
    void count() {
        final CouponPolicy couponPolicy = CouponPolicy.createWithFixedPolicy(new Name(), new Keyword(), new Period(), new Amount(), Count.valueOf(5));

        assertThat(couponPolicy.getCount()).isEqualTo(5);
    }
}
