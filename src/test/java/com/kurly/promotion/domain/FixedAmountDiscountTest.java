package com.kurly.promotion.domain;

import com.kurly.promotion.domain.vo.Period;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

class FixedAmountDiscountTest {

    @DisplayName("할인 가격을 설정할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 3000, 4000})
    void create(final int givenAmount) {
        final FixedAmountDiscount fixedAmount = new FixedAmountDiscount(new Period(), givenAmount);

        assertThat(fixedAmount.getFixedAmount()).isEqualTo(givenAmount);
    }

    @DisplayName("파라미터가 Null일 경우 예외를 던진다.")
    @ParameterizedTest
    @NullSource
    void create_with_null_param(final Integer givenAmount) {
        assertThatCode(() -> new FixedAmountDiscount(new Period(), givenAmount)).isInstanceOf(InvalidParameterException.class);
    }

}
