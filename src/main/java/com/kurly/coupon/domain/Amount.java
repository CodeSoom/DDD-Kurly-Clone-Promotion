package com.kurly.coupon.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Amount {

    private static final int MINIMUM_AMOUNT = 0;
    private static final String MINIMUM_NUMBER_REQUIRED = "0보다 작은 값이 입력될 수 없습니다.";

    private Integer value;

    private Amount(Integer value) {
        this.value = value;
    }

    public static Amount valueOf(Integer value) {
        if (value < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException(MINIMUM_NUMBER_REQUIRED);
        }
        return new Amount(value);
    }
}
