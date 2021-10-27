package com.kurly.coupon.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kurly.coupon.application.CouponService;
import com.kurly.coupon.domain.PolicyType;
import com.kurly.coupon.dto.CouponPolicyPublishData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CouponController.class)
class CouponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CouponService couponService;

    private CouponPolicyPublishData couponPolicyPublishData;
    private CouponPolicyPublishData invalidCouponPolicyPublishData;

    private final Long registeredId = 1L;

    @BeforeEach
    void setUp() {
        couponPolicyPublishData = CouponPolicyPublishData.builder()
                .name("amount policy")
                .amount(100)
                .count(100)
                .keyword("여름할인")
                .policyType(PolicyType.FIXED_AMOUNT)
                .startDate(LocalDate.of(2030, 1, 1))
                .endDate(LocalDate.of(2030, 12, 31))
                .build();

        invalidCouponPolicyPublishData = new CouponPolicyPublishData();
    }

    @DisplayName("POST 요청은 올바른 쿠폰정책 정보가 주어진다면 상태코드 201 Created 를 응답한다.")
    @Test
    void publishWithValidCouponRegisterData() throws Exception {
        given(couponService.publishCouponPolicy(any(CouponPolicyPublishData.class)))
                .willReturn(registeredId);

        mockMvc.perform(post("/coupons/policies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(couponPolicyPublishData)))
                .andExpect(header().string("location", "/coupons/policies/" + registeredId))
                .andExpect(status().isCreated());
    }

    @DisplayName("POST 요청은 올바르지 않은 쿠폰정책 정보가 주어진다면 상태코드 400 BadRequest 를 응답한다.")
    @Test
    void publishWithInValidDiscountRegisterData() throws Exception {
        mockMvc.perform(post("/coupons/policies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidCouponPolicyPublishData)))
                .andExpect(status().isBadRequest());
    }
}
