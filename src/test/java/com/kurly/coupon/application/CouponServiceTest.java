package com.kurly.coupon.application;

import com.kurly.coupon.application.exception.PolicyNotFoundException;
import com.kurly.coupon.application.factory.CouponFactory;
import com.kurly.coupon.domain.coupon.Coupon;
import com.kurly.coupon.domain.coupon.CouponCount;
import com.kurly.coupon.domain.policy.Amount;
import com.kurly.coupon.domain.policy.CouponPolicy;
import com.kurly.coupon.domain.policy.Keyword;
import com.kurly.coupon.domain.policy.MinimumRedeemPrice;
import com.kurly.coupon.domain.policy.Name;
import com.kurly.coupon.domain.policy.Period;
import com.kurly.coupon.domain.policy.TotalCount;
import com.kurly.coupon.dto.CouponIssueData;
import com.kurly.coupon.infra.CouponRepository;
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
    private CouponIssueData couponIssueData;
    private CouponIssueData invalidCouponIssueData;

    private CouponService couponService;
    private CouponFactory couponFactory = mock(CouponFactory.class);
    private CouponRepository couponRepository = mock(CouponRepository.class);

    private String givenKeyword;
    private Long givenUserId;
    private Integer givenCount;
    private Coupon createdCoupon;
    private CouponPolicy couponPolicy;

    @BeforeEach
    void setUp() {
        couponService = new CouponService(couponRepository, couponFactory);

        givenKeyword = "test keyword";
        givenCount = 2;
        givenUserId = 1L;
        couponPolicy = publishedCouponPolicy(givenKeyword);
        couponIssueData = new CouponIssueData(givenUserId, givenKeyword, givenCount);
        invalidCouponIssueData = CouponIssueData.builder().build();
        final CouponCount count = CouponCount.valueOf(givenCount);
        createdCoupon = Coupon.issueCoupon(couponPolicy, givenUserId, count);

        ReflectionTestUtils.setField(createdCoupon, "id", 1L);
    }

    @DisplayName("issueCoupon??? ????????? ???????????????.")
    @Test
    void issue_with_valid_coupon() {
        // given
        given(couponRepository.save(any(Coupon.class)))
                .willReturn(createdCoupon);
        given(couponFactory.issueCoupon(couponIssueData))
                .willReturn(createdCoupon);

        // when
        final Long id = couponService.issueCoupon(couponIssueData);

        // then
        assertThat(id).isEqualTo(1L);
        verify(couponRepository).save(any(Coupon.class));
    }

    @DisplayName("issueCoupon??? ???????????? ?????? ????????? ???????????? ????????? ????????????.")
    @Test
    void publish_with_invalid_coupon_policy() {
        given(couponFactory.issueCoupon(invalidCouponIssueData))
                .willThrow(PolicyNotFoundException.class);

        assertThatCode(() -> couponService.issueCoupon(invalidCouponIssueData))
                .isInstanceOf(PolicyNotFoundException.class);
    }

    private CouponPolicy publishedCouponPolicy(String givenKeyword) {
        final Name name = Name.valueOf("test ??????");
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
