package com.kurly.coupon.domain;

import javax.persistence.*;

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

    public FixedAmount(Name name, Keyword keyword, Period period, Amount amount, Count count) {
        super(name, keyword, period, PolicyType.FIXED_AMOUNT, count);
        this.amount = amount;
    }

    @Override
    public Integer getAmount() {
        return amount.getValue();
    }
}
