package com.kurly.coupon.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class CouponNumber {

    private String value;

    private CouponNumber(String value) {
        this.value = value;
    }

    public static CouponNumber of(String value) {
        return new CouponNumber(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CouponNumber)) return false;
        CouponNumber that = (CouponNumber) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
