package com.kurly.coupon.domain;

import com.kurly.common.model.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
     * 키워드(사용자쿠폰 입력시 사용).
     */
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "coupon_number", nullable = false))
    private Keyword keyword;

    /**
     * 기간.
     */
    @Embedded
    private Period period;

    /**
     * 정책.
     */
    @Enumerated(EnumType.STRING)
    private PolicyType policyType;

    /**
     * 수량.
     */
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "count"))
    private Count count;

    public CouponPolicy(Name name, Keyword keyword, Period period, PolicyType policyType, Count count) {
        this.name = name;
        this.keyword = keyword;
        this.period = period;
        this.policyType = policyType;
        this.count = count;
    }

    public static CouponPolicy publishFixedAmountPolicy(Name name, Keyword keyword, Period period, Amount amount, Count count) {
        return new FixedAmount(name, keyword, period, amount, count);
    }

    public static CouponPolicy publishRatePolicy(Name name, Keyword keyword, Period period, Rate rate, Count count) {
        return new FlatRate(name, keyword, period, rate, count);
    }

    public String getKeyword() {
        return keyword.getValue();
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
