package com.kurly.coupon.application;

import com.kurly.coupon.application.factory.FactoryPolicies;
import com.kurly.coupon.domain.CouponPolicy;
import com.kurly.coupon.dto.CouponPolicyRegisterData;
import com.kurly.coupon.infra.CouponPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CouponService {
    private final CouponPolicyRepository couponPolicyRepository;
    private final FactoryPolicies factoryPolicies;

    @Transactional
    public Long registerPolicy(CouponPolicyRegisterData policyRegisterDto) {
        final CouponPolicy couponPolicy = factoryPolicies.create(policyRegisterDto);
        final CouponPolicy save = couponPolicyRepository.save(couponPolicy);
        return save.getId();
    }
}
