package com.kurly.coupon.domain;

import javax.persistence.*;

/**
 * 정률 할인.
 */
@Entity
@DiscriminatorValue("RATE")
public class FlatRate extends CouponPolicy {

    /**
     * 할인 비율.
     */
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "rate"))
    private Rate rate;

    public FlatRate(Name name, Keyword keyword, Period period, Rate rate, Count count) {
        super(name, keyword, period, Policy.FLAT_RATE, count);
        this.rate = rate;
    }

    protected FlatRate() {
    }

    @Override
    public Integer getRate() {
        return rate.getValue();
    }
}
