package com.kurly.coupon.application;

import com.kurly.coupon.application.factory.FactoryPolicies;
import com.kurly.coupon.domain.Amount;
import com.kurly.coupon.domain.Count;
import com.kurly.coupon.domain.CouponCode;
import com.kurly.coupon.domain.CouponPolicy;
import com.kurly.coupon.domain.Name;
import com.kurly.coupon.domain.Period;
import com.kurly.coupon.domain.Policy;
import com.kurly.coupon.dto.CouponPolicyRegisterData;
import com.kurly.coupon.infra.CouponPolicyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CouponServiceTest {
    private CouponPolicyRegisterData couponPolicyRegisterData;
    private CouponPolicyRegisterData invalidCouponPolicyRegistrationData;

    private CouponService couponService;

    private CouponPolicyRepository couponPolicyRepository = mock(CouponPolicyRepository.class);

    private String givenName;
    private String givenCouponNumber;
    private Period givenPeriod;
    private Integer givenCount;
    private Integer givenAmount;
    private CouponPolicy createdPolicy;

    @BeforeEach
    void setUp() {
        givenName = "테스트 쿠폰";
        givenCouponNumber = "aaa-bbb-ccc-ddd";
        LocalDate givenStartDate = LocalDate.of(2030, 1, 1);
        LocalDate givenEndDate = LocalDate.of(2030, 12, 31);
        givenPeriod = Period.createPeriod(givenStartDate, givenEndDate);
        givenAmount = 1000;
        givenCount = 100;

        couponPolicyRegisterData = CouponPolicyRegisterData.builder()
                .name(givenName)
                .amount(givenAmount)
                .count(givenCount)
                .policy(Policy.FIXED_AMOUNT)
                .startDate(givenStartDate)
                .endDate(givenEndDate)
                .build();

        invalidCouponPolicyRegistrationData = CouponPolicyRegisterData.builder()
                .build();

        couponService = new CouponService(couponPolicyRepository, new FactoryPolicies());

        createdPolicy = CouponPolicy.createWithFixedPolicy(
                Name.of(givenName),
                CouponCode.of(givenCouponNumber),
                givenPeriod,
                Amount.valueOf(givenAmount),
                Count.valueOf(givenCount)
        );
        ReflectionTestUtils.setField(createdPolicy, "id", 1L);
    }

    @DisplayName("registerCouponPolicy는 쿠폰 정책을 등록합니다.")
    @Test
    void register_with_valid_coupon_policy() {
        // given
        given(couponPolicyRepository.save(any(CouponPolicy.class)))
                .willReturn(createdPolicy);

        // when
        final Long id = couponService.registerPolicy(couponPolicyRegisterData);

        // then
        assertThat(id).isEqualTo(1L);
        verify(couponPolicyRepository).save(any(CouponPolicy.class));
    }

    @DisplayName("registerCouponPolicy는 올바르지 않은 정보가 입력되면 예외를 던집니다.")
    @Test
    void register_with_invalid_coupon_policy() {
        assertThatCode(() -> couponService.registerPolicy(invalidCouponPolicyRegistrationData));
    }
}
