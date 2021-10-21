package com.kurly.coupon.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kurly.coupon.application.CouponService;
import com.kurly.coupon.domain.Policy;
import com.kurly.coupon.dto.CouponPolicyRegisterData;
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

    private CouponPolicyRegisterData couponPolicyRegisterData;
    private CouponPolicyRegisterData invalidCouponPolicyRegistrationData;

    private final Long registeredId = 1L;

    @BeforeEach
    void setUp() {
        couponPolicyRegisterData = CouponPolicyRegisterData.builder()
                .name("amount policy")
                .amount(100)
                .count(100)
                .policy(Policy.FIXED_AMOUNT)
                .startDate(LocalDate.of(2030, 1, 1))
                .endDate(LocalDate.of(2030, 12, 31))
                .build();

        invalidCouponPolicyRegistrationData = CouponPolicyRegisterData.builder()
                .build();
    }

    @DisplayName("POST 요청은 올바른 쿠폰 정보가 주어진다면 상태코드 201 Created 를 응답한다.")
    @Test
    void registerWithValidCouponRegisterData() throws Exception {
        given(couponService.registerPolicy(any(CouponPolicyRegisterData.class)))
                .willReturn(registeredId);

        mockMvc.perform(post("/coupons/policies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(couponPolicyRegisterData)))
                .andExpect(header().string("location", "/coupons/policies/" + registeredId))
                .andExpect(status().isCreated());
    }

    @DisplayName("POST 요청은 올바르지 않은 할인 정보가 주어진다면 상태코드 400 BadRequest 를 응답한다.")
    @Test
    void registerWithInValidDiscountRegisterData() throws Exception {
        mockMvc.perform(post("/coupons/policies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidCouponPolicyRegistrationData)))
                .andExpect(status().isBadRequest());
    }
}
