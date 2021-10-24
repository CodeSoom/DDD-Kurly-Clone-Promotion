package com.kurly.coupon.domain.policy;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

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

    public FlatRate(Name name, Keyword keyword, Period period, Rate rate, Count count, MinimumRedeemPrice minimumRedeemPrice) {
        super(name, keyword, period, count, minimumRedeemPrice);
        this.rate = rate;
    }

    protected FlatRate() {
    }

    @Override
    public Integer getRate() {
        return rate.getValue();
    }
}
