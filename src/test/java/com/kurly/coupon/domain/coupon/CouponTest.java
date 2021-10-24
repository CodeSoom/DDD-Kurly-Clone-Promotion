package com.kurly.coupon.domain.coupon;

import com.kurly.coupon.domain.policy.Amount;
import com.kurly.coupon.domain.policy.CouponPolicy;
import com.kurly.coupon.domain.policy.Keyword;
import com.kurly.coupon.domain.policy.MinimumRedeemPrice;
import com.kurly.coupon.domain.policy.Name;
import com.kurly.coupon.domain.policy.Period;
import com.kurly.coupon.domain.policy.TotalCount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class CouponTest {
    private CouponPolicy couponPolicy;

    @BeforeEach
    void setUp() {
        couponPolicy = publishedCouponPolicy();
    }

    @DisplayName("쿠폰을 사용자에게 발급 할 수 있다")
    @Test
    void create() {
        Long userId = 1L;
        CouponCount count = CouponCount.valueOf(2);

        Coupon actual = Coupon.issueCoupon(couponPolicy, userId, count);

        assertThat(actual).isNotNull();
    }

    @DisplayName("쿠폰은 상태를 가진다")
    @Test
    void initial_status() {
        final Long userId = 1L;
        final CouponCount count = CouponCount.valueOf(2);

        final Coupon actual = Coupon.issueCoupon(couponPolicy, userId, count);

        assertThat(actual.getCouponStatus()).isNotNull();
    }

    @DisplayName("쿠폰이 발급되면 쿠폰정책으로 만들어진 총 갯수가 감소한다")
    @Test
    void subtract_total_count() {
        final Long userId = 1L;
        final CouponCount count = CouponCount.valueOf(2);

        Coupon.issueCoupon(couponPolicy, userId, count);

        assertThat(couponPolicy.getTotalCount()).isEqualTo(3);
    }

    private CouponPolicy publishedCouponPolicy() {
        final Name name = Name.valueOf("test 쿠폰");
        final Keyword keyword = Keyword.valueOf("test 키워드");
        final LocalDate startDate = LocalDate.of(2030, 1, 1);
        final LocalDate endDate = LocalDate.of(2030, 12, 31);
        final Period period = Period.createPeriod(startDate, endDate);
        final TotalCount totalCount = TotalCount.valueOf(5);
        final MinimumRedeemPrice minimumRedeemPrice = MinimumRedeemPrice.valueOf(0);
        final Amount amount = Amount.valueOf(500);
        return CouponPolicy.publishFixedAmountPolicy(name, keyword, period, amount, totalCount, minimumRedeemPrice);
    }
}
