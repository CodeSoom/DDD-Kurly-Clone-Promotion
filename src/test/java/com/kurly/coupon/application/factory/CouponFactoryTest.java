package com.kurly.coupon.application.factory;

import com.kurly.coupon.application.exception.PolicyNotFoundException;
import com.kurly.coupon.domain.coupon.Coupon;
import com.kurly.coupon.domain.policy.Amount;
import com.kurly.coupon.domain.policy.CouponPolicy;
import com.kurly.coupon.domain.policy.Keyword;
import com.kurly.coupon.domain.policy.MinimumRedeemPrice;
import com.kurly.coupon.domain.policy.Name;
import com.kurly.coupon.domain.policy.Period;
import com.kurly.coupon.domain.policy.TotalCount;
import com.kurly.coupon.dto.CouponIssueData;
import com.kurly.coupon.infra.CouponPolicyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CouponFactoryTest {
    private CouponPolicyRepository couponPolicyRepository = mock(CouponPolicyRepository.class);

    private CouponFactory couponFactory;
    private CouponIssueData couponIssueData;
    private String givenKeyword;
    private int givenCount;
    private long givenUserId;
    private CouponPolicy couponPolicy;

    @BeforeEach
    void setUp() {
        couponFactory = new CouponFactory(couponPolicyRepository);
        givenKeyword = "test keyword";
        givenCount = 2;
        givenUserId = 1L;
        couponPolicy = publishedCouponPolicy(givenKeyword);
        couponIssueData = new CouponIssueData(givenUserId, givenKeyword, givenCount);
    }

    @DisplayName("쿠폰 발급 정보를 입력받아 쿠폰을 생성한다.")
    @Test
    void create_coupon() {
        given(couponPolicyRepository.findByKeyword(any(Keyword.class)))
                .willReturn(Optional.of(couponPolicy));
        // when
        final Coupon actual = couponFactory.issueCoupon(couponIssueData);

        // then
        assertThat(actual).isNotNull();
    }

    @DisplayName("존재하지 않는 쿠폰정책을 입력받으면 예외를 던진다.")
    @Test
    void issue_with_not_exist_policy() {
        given(couponPolicyRepository.findByKeyword(any(Keyword.class)))
                .willThrow(PolicyNotFoundException.class);

        assertThatCode(() -> couponFactory.issueCoupon(couponIssueData))
                .isInstanceOf(PolicyNotFoundException.class);
    }

    private CouponPolicy publishedCouponPolicy(String givenKeyword) {
        final Name name = Name.valueOf("test 쿠폰");
        final Keyword keyword = Keyword.valueOf(givenKeyword);
        final LocalDate startDate = LocalDate.of(2030, 1, 1);
        final LocalDate endDate = LocalDate.of(2030, 12, 31);
        final Period period = Period.createPeriod(startDate, endDate);
        final TotalCount totalCount = TotalCount.valueOf(10);
        final MinimumRedeemPrice minimumRedeemPrice = MinimumRedeemPrice.valueOf(0);
        final Amount amount = Amount.valueOf(500);
        return CouponPolicy.publishFixedAmountPolicy(name, keyword, period, amount, totalCount, minimumRedeemPrice);
    }
}
