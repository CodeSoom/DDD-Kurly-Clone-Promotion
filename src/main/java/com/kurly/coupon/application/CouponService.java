package com.kurly.coupon.application;

import com.kurly.coupon.application.factory.FactoryPolicies;
import com.kurly.coupon.domain.CouponPolicy;
import com.kurly.coupon.domain.Keyword;
import com.kurly.coupon.domain.Name;
import com.kurly.coupon.dto.CouponPolicyPublishData;
import com.kurly.coupon.infra.CouponPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CouponService {
    private static final String ALREADY_EXISTED_POLICY = "이미 존재하는 정책입니다.";

    private final CouponPolicyRepository couponPolicyRepository;
    private final FactoryPolicies factoryPolicies;

    @Transactional
    public Long publishCouponPolicy(CouponPolicyPublishData policyRegisterDto) {
        if (!findPublishedPolicy(policyRegisterDto).isEmpty()) {
            throw new IllegalArgumentException(ALREADY_EXISTED_POLICY);
        }
        final CouponPolicy couponPolicy = factoryPolicies.publishPolicy(policyRegisterDto);
        final CouponPolicy savedPolicy = couponPolicyRepository.save(couponPolicy);
        return savedPolicy.getId();
    }

    private List<CouponPolicy> findPublishedPolicy(CouponPolicyPublishData policyRegisterDto) {
        final Name name = Name.valueOf(policyRegisterDto.getName());
        final Keyword keyword = Keyword.valueOf(policyRegisterDto.getKeyword());
        return couponPolicyRepository.findByNameOrKeyword(name, keyword);
    }
}
