package com.kurly.coupon.application.factory;

import com.kurly.coupon.application.exception.PolicyNotFoundException;
import com.kurly.coupon.domain.coupon.Coupon;
import com.kurly.coupon.domain.coupon.CouponCount;
import com.kurly.coupon.domain.policy.CouponPolicy;
import com.kurly.coupon.domain.policy.Keyword;
import com.kurly.coupon.dto.CouponIssueData;
import com.kurly.coupon.infra.CouponPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CouponFactory {
    private static final String NOT_FOUND_KEYWORD = "찾을 수 없는 키워드 : ";

    private final CouponPolicyRepository couponPolicyRepository;

    public Coupon issueCoupon(CouponIssueData dto) {
        final Keyword keyword = Keyword.valueOf(dto.getKeyword());
        final CouponPolicy policy = findPolicy(keyword);
        final CouponCount count = CouponCount.valueOf(dto.getCount());

        return Coupon.issueCoupon(policy, dto.getUserId(), count);
    }

    private CouponPolicy findPolicy(Keyword keyword) {
        return couponPolicyRepository.findByKeyword(keyword)
                .orElseThrow(() -> new PolicyNotFoundException(NOT_FOUND_KEYWORD + keyword));
    }

}
