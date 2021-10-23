package com.kurly.coupon.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class MinimumRedeemPriceTest {

    @DisplayName("최소 적용 금액을 생성할 수 있다")
    @ParameterizedTest
    @ValueSource(ints = {1000, 10_000, 1000_000})
    void create(int givenPrice) {
        assertThat(MinimumRedeemPrice.valueOf(givenPrice)).isNotNull();
    }

    @DisplayName("최소 적용 금액을 음수로 입력시 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(ints = {-1000, -10_000, -1000_000})
    void create_with_negative_number(int givenPrice) {
        assertThatCode(() -> MinimumRedeemPrice.valueOf(givenPrice)).isInstanceOf(IllegalArgumentException.class);
    }
}
