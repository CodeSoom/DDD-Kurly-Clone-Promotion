package com.kurly.coupon.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Rate {
    private static final int MINIMUM_RATE = 0;
    private static final int MAXIMUM_RATE = 100;

    private Integer value;

    private Rate(Integer value) {
        this.value = value;
    }

    public static Rate valueOf(Integer rate) {
        if (rate <= MINIMUM_RATE) {
            throw new IllegalArgumentException("0보다 작은 값을 입력할 수 없습니다.");
        }
        if (rate > MAXIMUM_RATE) {
            throw new IllegalArgumentException("최대 할인 값을 초과하였습니다.");
        }
        return new Rate(rate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rate)) return false;
        Rate rate = (Rate) o;
        return Objects.equals(getValue(), rate.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return "Rate{" +
                "value=" + value +
                '}';
    }
}
