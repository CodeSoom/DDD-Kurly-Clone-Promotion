package com.kurly.promotion.domain;

import com.kurly.promotion.domain.vo.Period;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.InvalidParameterException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class FlatRateDiscountTest {
    /*private static final Period PERIOD = Period.builder()
            .startDate(LocalDate.of(2030, 1, 1))
            .endDate(LocalDate.of(2030, 12, 1))
            .build();*/

    @DisplayName("할인 비율을 설정할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {10, 20, 30, 40})
    void create(final int givenRate) {
        final FlatRateDiscount flatRate = new FlatRateDiscount(new Period(), givenRate);

        assertThat(flatRate.getFlatRate()).isEqualTo(givenRate);
    }

    @DisplayName("파라미터가 Null일 경우 예외를 던진다.")
    @ParameterizedTest
    @NullSource
    void create_with_null_param(final Integer givenRate) {
        assertThatCode(() -> new FlatRateDiscount(new Period(), givenRate)).isInstanceOf(InvalidParameterException.class);
    }

    @DisplayName("0보다 작은 할인 비율 입력시 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(ints = {-10, -20, -30, -40})
    void create_with_negative_rate(final int givenRate) {
        assertThatCode(() -> new FlatRateDiscount(new Period(), givenRate)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("100보다 큰 할인 비율 입력시 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(ints = {110, 150, 200, 300})
    void create_with_over_hundred_rate(final int givenRate) {
        assertThatCode(() -> new FlatRateDiscount(new Period(), givenRate)).isInstanceOf(IllegalArgumentException.class);
    }

}
