package com.kurly.coupon.application;

import com.kurly.coupon.application.factory.FactoryPolicies;
import com.kurly.coupon.domain.policy.Amount;
import com.kurly.coupon.domain.policy.CouponPolicy;
import com.kurly.coupon.domain.policy.Keyword;
import com.kurly.coupon.domain.policy.MinimumRedeemPrice;
import com.kurly.coupon.domain.policy.Name;
import com.kurly.coupon.domain.policy.Period;
import com.kurly.coupon.domain.policy.PolicyType;
import com.kurly.coupon.domain.policy.TotalCount;
import com.kurly.coupon.dto.CouponPolicyPublishData;
import com.kurly.coupon.infra.CouponPolicyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CouponPolicyServiceTest {
    private CouponPolicyPublishData couponPolicyPublishData;
    private CouponPolicyPublishData invalidCouponPolicyPublishData;

    private CouponPolicyService couponPolicyService;

    private CouponPolicyRepository couponPolicyRepository = mock(CouponPolicyRepository.class);

    private String givenName;
    private String givenKeyword;
    private Period givenPeriod;
    private Integer givenCount;
    private Integer givenAmount;
    private Integer givenPrice;
    private CouponPolicy createdPolicy;

    @BeforeEach
    void setUp() {
        givenName = "테스트 쿠폰";
        givenKeyword = "여름할인";
        LocalDate givenStartDate = LocalDate.of(2030, 1, 1);
        LocalDate givenEndDate = LocalDate.of(2030, 12, 31);
        givenPeriod = Period.createPeriod(givenStartDate, givenEndDate);
        givenAmount = 1000;
        givenCount = 100;
        givenPrice = 10000;

        couponPolicyPublishData = CouponPolicyPublishData.builder()
                .name(givenName)
                .amount(givenAmount)
                .count(givenCount)
                .policyType(PolicyType.FIXED_AMOUNT)
                .startDate(givenStartDate)
                .endDate(givenEndDate)
                .minimumRedeemPrice(givenPrice)
                .build();

        invalidCouponPolicyPublishData = CouponPolicyPublishData.builder()
                .build();

        couponPolicyService = new CouponPolicyService(couponPolicyRepository, new FactoryPolicies());

        createdPolicy = CouponPolicy.publishFixedAmountPolicy(
                Name.valueOf(givenName),
                Keyword.valueOf(givenKeyword),
                givenPeriod,
                Amount.valueOf(givenAmount),
                TotalCount.valueOf(givenCount),
                MinimumRedeemPrice.valueOf(givenPrice)
        );
        ReflectionTestUtils.setField(createdPolicy, "id", 1L);
    }

    @DisplayName("publishCouponPolicy는 쿠폰 정책을 등록합니다.")
    @Test
    void publish_with_valid_coupon_policy() {
        // given
        given(couponPolicyRepository.save(any(CouponPolicy.class)))
                .willReturn(createdPolicy);

        // when
        final Long id = couponPolicyService.publishCouponPolicy(couponPolicyPublishData);

        // then
        assertThat(id).isEqualTo(1L);
        verify(couponPolicyRepository).save(any(CouponPolicy.class));
    }

    @DisplayName("publishCouponPolicy는 올바르지 않은 정보가 입력되면 예외를 던집니다.")
    @Test
    void publish_with_invalid_coupon_policy() {
        assertThatCode(() -> couponPolicyService.publishCouponPolicy(invalidCouponPolicyPublishData))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("publishCouponPolicy는 이미 정책이 등록이 되어있으면 예외를 던집니다.")
    @Test
    void publish_with_exist_coupon_policy() {
        // given
        given(couponPolicyRepository.findByNameOrKeyword(any(Name.class), any(Keyword.class)))
                .willReturn(List.of(createdPolicy));

        assertThatCode(() -> couponPolicyService.publishCouponPolicy(invalidCouponPolicyPublishData))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
