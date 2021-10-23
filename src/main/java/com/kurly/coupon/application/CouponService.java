package com.kurly.coupon.application;

import com.kurly.coupon.application.factory.FactoryPolicies;
import com.kurly.coupon.domain.CouponPolicies;
import com.kurly.coupon.domain.CouponPolicy;
import com.kurly.coupon.dto.CouponPolicyPublishData;
import com.kurly.coupon.infra.CouponPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CouponService {
    private static final String ALREADY_EXISTED_POLICY = "이미 존재하는 정책입니다.";

    private final CouponPolicyRepository couponPolicyRepository;
    private final FactoryPolicies factoryPolicies;

    @Transactional
    public Long publishCouponPolicy(CouponPolicyPublishData dto) {
        checkCouponDuplicated(dto);
        final CouponPolicy couponPolicy = factoryPolicies.publishPolicy(dto);
        final CouponPolicy savedPolicy = couponPolicyRepository.save(couponPolicy);
        return savedPolicy.getId();
    }

    private void checkCouponDuplicated(CouponPolicyPublishData dto) {
        final CouponPolicies couponPolicies = findPublishedPolicy(dto);
        if (couponPolicies.isKeywordDuplicated(dto.convertKeywordVO()) ||
                couponPolicies.isNameDuplicated(dto.convertNameVO())) {
            throw new IllegalArgumentException(ALREADY_EXISTED_POLICY);
        }
    }

    private CouponPolicies findPublishedPolicy(CouponPolicyPublishData dto) {
        return new CouponPolicies(couponPolicyRepository.findByNameOrKeyword(dto.convertNameVO(), dto.convertKeywordVO()));
    }
}
