package com.kurly.coupon.domain.policy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class AmountTest {

    @DisplayName("할인 값을 생할 수 있다")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100})
    void create(int givenCount) {
        assertThat(Amount.valueOf(givenCount)).isNotNull();
    }

    @DisplayName("음수로 입력시 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -10, -100})
    void create_with_negative_number(int givenCount) {
        assertThatCode(() -> Amount.valueOf(givenCount)).isInstanceOf(IllegalArgumentException.class);
    }
}
