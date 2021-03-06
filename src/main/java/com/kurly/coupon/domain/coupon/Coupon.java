package com.kurly.coupon.domain.coupon;

import com.kurly.common.model.BaseEntity;
import com.kurly.coupon.domain.policy.CouponPolicy;
import com.kurly.coupon.domain.policy.Keyword;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_policy_id")
    private CouponPolicy couponPolicy;

    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    private CouponStatus couponStatus;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "coupon_count", nullable = false))
    private CouponCount couponCount;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "keyword", nullable = false, unique = true))
    private Keyword keyword;

    private Coupon(Long id, CouponPolicy couponPolicy, Long userId, CouponStatus couponStatus, CouponCount couponCount) {
        this.id = id;
        this.couponPolicy = couponPolicy;
        this.userId = userId;
        this.couponStatus = couponStatus;
        this.couponCount = couponCount;
        this.keyword = Keyword.valueOf(couponPolicy.getKeyword());
    }

    public static Coupon issueCoupon(CouponPolicy couponPolicy, Long userId, CouponCount count) {
        couponPolicy.decreaseCount(count.getValue());
        return new Coupon(null, couponPolicy, userId, CouponStatus.ISSUED, count);
    }

    public void increaseCouponCount(Integer count) {
        this.couponPolicy.decreaseCount(count);
        this.couponCount = couponCount.increaseCount(count);
    }

    public Integer getCouponCount() {
        return couponCount.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coupon)) return false;
        Coupon coupon = (Coupon) o;
        return Objects.equals(id, coupon.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
