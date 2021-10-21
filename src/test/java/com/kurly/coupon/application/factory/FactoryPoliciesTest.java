package com.kurly.coupon.application.factory;

import com.kurly.coupon.domain.CouponPolicy;
import com.kurly.coupon.domain.FixedAmount;
import com.kurly.coupon.domain.FlatRate;
import com.kurly.coupon.domain.Policy;
import com.kurly.coupon.dto.CouponPolicyRegisterData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class FactoryPoliciesTest {

    private CouponPolicyRegisterData flatRateRegisterData;
    private CouponPolicyRegisterData fixedAmountRegisterData;

    private FactoryPolicies factoryPolicies;

    private String givenName;
    private Integer givenCount;
    private Integer givenAmount;
    private Integer givenRate;

    @BeforeEach
    void setUp() {
        factoryPolicies = new FactoryPolicies();

        givenName = "테스트 쿠폰";
        LocalDate givenStartDate = LocalDate.of(2030, 1, 1);
        LocalDate givenEndDate = LocalDate.of(2030, 12, 31);
        givenAmount = 1000;
        givenRate = 10;
        givenCount = 100;

        flatRateRegisterData = CouponPolicyRegisterData.builder()
                .name(givenName)
                .rate(givenRate)
                .count(givenCount)
                .policy(Policy.FLAT_RATE)
                .startDate(givenStartDate)
                .endDate(givenEndDate)
                .build();

        fixedAmountRegisterData = CouponPolicyRegisterData.builder()
                .name(givenName)
                .amount(givenAmount)
                .count(givenCount)
                .policy(Policy.FIXED_AMOUNT)
                .startDate(givenStartDate)
                .endDate(givenEndDate)
                .build();
    }

    @DisplayName("정률 정책을 입력받아 정률정책 객체를 생성한다")
    @Test
    void create_flat_rate() {
        // when
        final CouponPolicy actual = factoryPolicies.create(flatRateRegisterData);

        // then
        assertThat(actual).isInstanceOf(FlatRate.class);
    }

    @DisplayName("정액 정책을 입력받아 정액정책 객체를 생성한다")
    @Test
    void create_fixed_amount() {
        // when
        final CouponPolicy actual = factoryPolicies.create(fixedAmountRegisterData);

        // then
        assertThat(actual).isInstanceOf(FixedAmount.class);
    }
}
