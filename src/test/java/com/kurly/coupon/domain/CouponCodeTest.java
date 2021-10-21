package com.kurly.coupon.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CouponCodeTest {

    @DisplayName("쿠폰 코드를 생성할 수 있다.")
    @Test
    void crate() {
        final String givenNumber = UUID.randomUUID().toString();
        final CouponCode couponCode = CouponCode.of(givenNumber);

        assertThat(couponCode.getValue()).isEqualTo(givenNumber);
    }
}
