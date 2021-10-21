package com.kurly.coupon.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class CouponCode {

    private String value;

    private CouponCode(String value) {
        this.value = value;
    }

    public static CouponCode of(String value) {
        return new CouponCode(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CouponCode)) return false;
        CouponCode that = (CouponCode) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
