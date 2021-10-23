package com.kurly.coupon.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CouponPoliciesTest {

    @Test
    void create() {
        CouponPolicy couponPolicy = CouponPolicy.publishRatePolicy(
                new Name(), new Keyword(),
                new Period(), new Rate(),
                new Count(), null
        );
        CouponPolicies couponPolicies = new CouponPolicies(List.of(couponPolicy));

        assertThat(couponPolicies).isNotNull();
    }

    @DisplayName("쿠폰정책의 이름은 중복될 수 없다.")
    @Test
    void duplicated_name() {
        final Name givenName = Name.valueOf("test");
        final CouponPolicy couponPolicy = CouponPolicy.publishRatePolicy(
                givenName, new Keyword(),
                new Period(), new Rate(),
                new Count(), null
        );
        final CouponPolicies couponPolicies = new CouponPolicies(List.of(couponPolicy));

        assertThat(couponPolicies.isNameDuplicated(givenName)).isTrue();
    }

    @DisplayName("쿠폰정책의 키워드는 중복될 수 없다.")
    @Test
    void duplicated_keyword() {
        final Keyword givenKeyword = Keyword.valueOf("test");
        final CouponPolicy couponPolicy = CouponPolicy.publishRatePolicy(
                new Name(), givenKeyword,
                new Period(), new Rate(),
                new Count(), null
        );
        final CouponPolicies couponPolicies = new CouponPolicies(List.of(couponPolicy));

        assertThat(couponPolicies.isKeywordDuplicated(givenKeyword)).isTrue();
    }
}
