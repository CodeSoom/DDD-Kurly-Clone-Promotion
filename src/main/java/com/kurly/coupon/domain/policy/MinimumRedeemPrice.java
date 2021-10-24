package com.kurly.coupon.domain.policy;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class MinimumRedeemPrice {
    private static final int MINIMUM_AMOUNT = 0;
    private static final String MINIMUM_NUMBER_REQUIRED = "0보다 작은 값이 입력될 수 없습니다.";

    private Integer value;

    private MinimumRedeemPrice(Integer value) {
        this.value = value;
    }

    public static MinimumRedeemPrice valueOf(Integer value) {
        if (Objects.nonNull(value) && value < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException(MINIMUM_NUMBER_REQUIRED);
        }
        return new MinimumRedeemPrice(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MinimumRedeemPrice)) return false;
        MinimumRedeemPrice that = (MinimumRedeemPrice) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
