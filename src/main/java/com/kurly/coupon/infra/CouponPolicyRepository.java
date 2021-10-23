package com.kurly.coupon.infra;

import com.kurly.coupon.domain.CouponPolicy;
import com.kurly.coupon.domain.Keyword;
import com.kurly.coupon.domain.Name;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponPolicyRepository extends JpaRepository<CouponPolicy, Long> {
    List<CouponPolicy> findByNameOrKeyword(Name name, Keyword keyword);
}
