package com.kurly.coupon.domain.coupon;

import com.kurly.common.model.BaseEntity;
import com.kurly.coupon.domain.policy.CouponPolicy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private CouponCount couponCount;

    private Coupon(Long id, CouponPolicy couponPolicy, Long userId, CouponStatus couponStatus, CouponCount couponCount) {
        this.id = id;
        this.couponPolicy = couponPolicy;
        this.userId = userId;
        this.couponStatus = couponStatus;
        this.couponCount = couponCount;
    }

    public static Coupon issueCoupon(CouponPolicy couponPolicy, Long userId, CouponCount count) {
        couponPolicy.subtractCount(count.getValue());
        return new Coupon(null, couponPolicy, userId, CouponStatus.ISSUED, count);
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
