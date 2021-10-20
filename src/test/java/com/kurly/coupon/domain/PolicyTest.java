package com.kurly.coupon.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PolicyTest {

    @DisplayName("쿠폰 정책은 정액할인과 정률할인으로 구성된다.")
    @Test
    void policy() {
        assertThat(Policy.values()).containsExactlyInAnyOrder(Policy.FLAT, Policy.FIXED);
    }
}
