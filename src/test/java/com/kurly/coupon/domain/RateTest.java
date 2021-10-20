package com.kurly.coupon.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class RateTest {

    @DisplayName("할인 비율을 설정할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {10, 20, 30, 40})
    void create(final int givenRate) {
        final Rate rate = Rate.of(givenRate);

        assertThat(rate.getValue()).isEqualTo(givenRate);
    }

    @DisplayName("0보다 작은 할인 비율 입력시 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(ints = {-10, -20, -30, -40})
    void create_with_negative_rate(final int givenRate) {
        assertThatCode(() -> Rate.of(givenRate)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("100보다 큰 할인 비율 입력시 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(ints = {110, 150, 200, 300})
    void create_with_over_hundred_rate(final int givenRate) {
        assertThatCode(() -> Rate.of(givenRate)).isInstanceOf(IllegalArgumentException.class);
    }
}
