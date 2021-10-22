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
    @AttributeOverride(name = "value", column = @Column(name = "name", nullable = false, unique = true))
    private Name name;

    /**
     * 키워드(사용자쿠폰 입력시 사용).
     */
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "keyword", nullable = false, unique = true))
    private Keyword keyword;

    /**
     * 기간.
     */
    @Embedded
    private Period period;

    /**
     * 수량.
     */
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "count", nullable = false))
    private Count count;

    /**
     * 최소 적용 가격.
     */
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "min_redeem_price_"))
    private MinimumRedeemPrice minimumRedeemPrice;

    public CouponPolicy(Name name, Keyword keyword, Period period, Count count, MinimumRedeemPrice minimumRedeemPrice) {
        this.name = name;
        this.keyword = keyword;
        this.period = period;
        this.count = count;
        this.minimumRedeemPrice = minimumRedeemPrice;
    }

    public static CouponPolicy publishFixedAmountPolicy(Name name, Keyword keyword, Period period, Amount amount, Count count, MinimumRedeemPrice minimumRedeemPrice) {
        return new FixedAmount(name, keyword, period, amount, count, minimumRedeemPrice);
    }

    public static CouponPolicy publishRatePolicy(Name name, Keyword keyword, Period period, Rate rate, Count count, MinimumRedeemPrice minimumRedeemPrice) {
        return new FlatRate(name, keyword, period, rate, count, minimumRedeemPrice);
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

    public Integer getMinimumRedeemPrice() {
        return minimumRedeemPrice.getValue();
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
