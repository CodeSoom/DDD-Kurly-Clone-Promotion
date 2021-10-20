package com.kurly.promotion.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kurly.promotion.application.DiscountService;
import com.kurly.promotion.dto.DiscountRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DiscountController.class)
class DiscountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DiscountService discountService;

    private DiscountRegistrationData discountRegistrationData;
    private DiscountRegistrationData invalidDiscountRegistrationData;

    private final Long registeredId = 1L;

    @BeforeEach
    void setUp() {
        discountRegistrationData = DiscountRegistrationData.builder()
                .productId(1L)
                .flatRate(25)
                .build();

        invalidDiscountRegistrationData = DiscountRegistrationData.builder()
                .productId(1L)
                .build();
    }

    @DisplayName("POST 요청은 올바른 할인 정보가 주어진다면 상태코드 201 Created 를 응답한다.")
    @Test
    void registerWithValidDiscountRegisterData() throws Exception {
        given(discountService.registerDiscount(any(Integer.class), any(Long.class)))
                .willReturn(registeredId);

        mockMvc.perform(post("/discounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(discountRegistrationData)))
                .andExpect(header().string("location", "/discounts/" + registeredId))
                .andExpect(status().isCreated());
    }

    @DisplayName("POST 요청은 올바르지 않은 할인 정보가 주어진다면 상태코드 400 BadRequest 를 응답한다.")
    @Test
    void registerWithInValidDiscountRegisterData() throws Exception {
        mockMvc.perform(post("/discounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDiscountRegistrationData)))
                .andExpect(status().isBadRequest());
    }
}
