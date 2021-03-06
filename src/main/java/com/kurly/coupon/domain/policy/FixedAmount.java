package com.kurly.coupon.domain.policy;

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

    public FixedAmount(Name name, Keyword keyword, Period period, Amount amount, TotalCount totalCount, MinimumRedeemPrice minimumRedeemPrice) {
        super(name, keyword, period, totalCount, minimumRedeemPrice);
        this.amount = amount;
    }

    protected FixedAmount() {
    }

    @Override
    public Integer getAmount() {
        return amount.getValue();
    }
}
