package com.kurly.coupon.domain;

import com.kurly.common.model.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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
    private CouponCode couponCode;

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
     * 수량.
     */
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "count"))
    private Count count;

    public CouponPolicy(Name name, CouponCode couponCode, Period period, Policy policy, Count count) {
        this.name = name;
        this.couponCode = couponCode;
        this.period = period;
        this.policy = policy;
        this.count = count;
    }

    public static CouponPolicy createWithFixedPolicy(Name name, CouponCode couponCode, Period period, Amount amount, Count count) {
        return new FixedAmount(name, couponCode, period, amount, count);
    }

    public static CouponPolicy createWithRatePolicy(Name name, CouponCode couponCode, Period period, Rate rate, Count count) {
        return new FlatRate(name, couponCode, period, rate, count);
    }

    public String getCouponCode() {
        return couponCode.getValue();
    }

    public Period getPeriod() {
        return period;
    }

    public Integer getCount() {
        return count.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public Integer getAmount() {
        return null;
    }

    public Integer getRate() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CouponPolicy)) return false;
        CouponPolicy that = (CouponPolicy) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
