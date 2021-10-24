package com.kurly.coupon.application.factory;

import com.kurly.coupon.domain.policy.CouponPolicy;
import com.kurly.coupon.domain.policy.PolicyType;
import com.kurly.coupon.dto.CouponPolicyPublishData;

public interface PolicyFactory {

    boolean matchPolicy(PolicyType policyType);

    CouponPolicy publishPolicy(CouponPolicyPublishData dto);
}
