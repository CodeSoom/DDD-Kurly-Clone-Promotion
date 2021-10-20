package com.kurly.coupon.infra;

import com.kurly.coupon.domain.CouponPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponPolicyRepository extends JpaRepository<CouponPolicy, Long> {
}
