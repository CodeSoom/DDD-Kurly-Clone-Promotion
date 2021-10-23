package com.kurly.coupon.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Count {
    private static final int MINIMUM_AMOUNT = 0;
    private static final String MINIMUM_NUMBER_REQUIRED = "0보다 작은 값이 입력될 수 없습니다.";

    private Integer value;

    private Count(Integer value) {
        this.value = value;
    }

    public static Count valueOf(Integer value) {
        if (value < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException(MINIMUM_NUMBER_REQUIRED);
        }
        return new Count(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Count)) return false;
        Count count = (Count) o;
        return Objects.equals(getValue(), count.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return "Count{" +
                "value=" + value +
                '}';
    }
}
