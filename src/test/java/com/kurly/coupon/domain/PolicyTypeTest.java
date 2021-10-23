package com.kurly.coupon.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PolicyTypeTest {

    @DisplayName("쿠폰 정책은 정액할인과 정률할인으로 구성된다.")
    @Test
    void policy() {
        assertThat(PolicyType.values()).containsExactlyInAnyOrder(PolicyType.FLAT_RATE, PolicyType.FIXED_AMOUNT);
    }
}
