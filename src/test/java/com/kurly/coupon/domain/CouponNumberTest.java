package com.kurly.coupon.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CouponNumberTest {

    @DisplayName("쿠폰 번호를 생성할 수 있다.")
    @Test
    void crate() {
        final String givenNumber = UUID.randomUUID().toString();
        final CouponNumber couponNumber = CouponNumber.of(givenNumber);

        assertThat(couponNumber.getValue()).isEqualTo(givenNumber);
    }
}
