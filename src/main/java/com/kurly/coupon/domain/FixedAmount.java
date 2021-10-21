package com.kurly.coupon.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * 정액 할인.
 */
@Entity
@DiscriminatorValue("AMOUNT")
public class FixedAmount extends CouponPolicy {

    /**
     * 할인 값.
     */
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "amount"))
    private Amount amount;

    protected FixedAmount() {
    }

    public FixedAmount(Name name, CouponCode couponCode, Period period, Amount amount, Count count) {
        super(name, couponCode, period, Policy.FIXED_AMOUNT, count);
        this.amount = amount;
    }

    @Override
    public Integer getAmount() {
        return amount.getValue();
    }
}
