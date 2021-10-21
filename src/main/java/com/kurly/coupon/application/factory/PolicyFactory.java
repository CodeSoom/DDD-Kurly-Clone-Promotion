package com.kurly.coupon.application.factory;

import com.kurly.coupon.domain.CouponPolicy;
import com.kurly.coupon.domain.PolicyType;
import com.kurly.coupon.dto.CouponPolicyPublishData;

public interface PolicyFactory {

    boolean matchPolicy(PolicyType policyType);

    CouponPolicy publishPolicy(CouponPolicyPublishData dto);
}
