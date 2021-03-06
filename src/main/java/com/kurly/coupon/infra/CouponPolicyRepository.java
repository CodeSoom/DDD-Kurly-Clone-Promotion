package com.kurly.coupon.infra;

import com.kurly.coupon.domain.policy.CouponPolicy;
import com.kurly.coupon.domain.policy.Keyword;
import com.kurly.coupon.domain.policy.Name;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CouponPolicyRepository extends JpaRepository<CouponPolicy, Long> {
    List<CouponPolicy> findByNameOrKeyword(Name name, Keyword keyword);

    Optional<CouponPolicy> findByKeyword(Keyword keyword);
}
