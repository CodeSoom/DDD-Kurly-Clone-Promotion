package com.kurly.coupon.domain;

import com.kurly.common.model.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CouponPolicy extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name", nullable = false))
    private Name name;

    /**
     * 쿠폰번호.
     */
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "coupon_number", nullable = false))
    private CouponNumber couponNumber;

    /**
     * 기간.
     */
    @Embedded
    private Period period;

    /**
     * 정책.
     */
    @Enumerated(EnumType.STRING)
    private Policy policy;

    /**
     * 할인 비율.
     */
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "rate"))
    private Rate rate;

    /**
     * 할인 값.
     */
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "amount"))
    private Amount amount;

    /**
     * 수량.
     */
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "count"))
    private Count count;

    private CouponPolicy(String name, String couponNumber, Period period, Policy policy, Rate rate, Count count, Amount amount) {
        this.name = Name.of(name);
        this.couponNumber = new CouponNumber(couponNumber);
        this.period = period;
        this.policy = policy;
        this.rate = rate;
        this.count = count;
        this.amount = amount;
    }

    public static CouponPolicy createWithFixedPolicy(String name, String couponNumber, Period period, int count, int amount) {
        return new CouponPolicy(name, couponNumber, period, Policy.FIXED, null, Count.valueOf(count), Amount.valueOf(amount));
    }

    public static CouponPolicy createWithRatePolicy(String name, String couponNumber, Period period, int rate, int count) {
        return new CouponPolicy(name, couponNumber, period, Policy.FLAT, Rate.of(rate), Count.valueOf(count), null);
    }

    public String getCouponNumber() {
        return couponNumber.getValue();
    }

    public Period getPeriod() {
        return period;
    }

    public Integer getRate() {
        return rate.getValue();
    }

    public Integer getCount() {
        return count.getValue();
    }

    public Integer getAmount() {
        return amount.getValue();
    }

    public String getName() {
        return name.getValue();
    }
}
