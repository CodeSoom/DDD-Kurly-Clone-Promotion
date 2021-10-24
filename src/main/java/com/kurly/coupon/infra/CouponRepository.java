package com.kurly.coupon.infra;

import com.kurly.coupon.domain.coupon.Coupon;
import com.kurly.coupon.domain.policy.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByUserIdAndKeyword(Long userId, Keyword keyword);
}
