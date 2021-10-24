package com.kurly.coupon.domain.policy;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class TotalCount {
    private static final int MINIMUM_AMOUNT = 0;
    private static final String MINIMUM_NUMBER_REQUIRED = "0보다 작은 값이 입력될 수 없습니다.";

    private Integer value;

    private TotalCount(Integer value) {
        this.value = value;
    }

    public static TotalCount valueOf(Integer value) {
        if (value < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException(MINIMUM_NUMBER_REQUIRED);
        }
        return new TotalCount(value);
    }

    public TotalCount decreaseCount(Integer value) {
        return new TotalCount(this.value - value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TotalCount)) return false;
        TotalCount totalCount = (TotalCount) o;
        return Objects.equals(getValue(), totalCount.getValue());
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
