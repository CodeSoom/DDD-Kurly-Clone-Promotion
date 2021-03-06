package com.kurly.promotion.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kurly.promotion.application.DiscountService;
import com.kurly.promotion.domain.Discount;
import com.kurly.promotion.dto.DiscountRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DiscountController.class)
class DiscountControllerTest {

    private final Long registeredId = 1L;
    private final PageRequest pageRequest = PageRequest.of(0, 10);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DiscountService discountService;
    private DiscountRegistrationData discountRegistrationData;
    private DiscountRegistrationData invalidDiscountRegistrationData;
    private List<Discount> discountList;

    @BeforeEach
    void setUp() {
        discountRegistrationData = DiscountRegistrationData.builder()
                .productId(1L)
                .flatRate(25)
                .build();

        invalidDiscountRegistrationData = DiscountRegistrationData.builder()
                .productId(1L)
                .build();

        this.discountList = List.of(
                new Discount(1L, 10, 1004L)
        );
    }

    @DisplayName("POST ????????? ????????? ?????? ????????? ??????????????? ???????????? 201 Created ??? ????????????.")
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

    @DisplayName("POST ????????? ???????????? ?????? ?????? ????????? ??????????????? ???????????? 400 BadRequest ??? ????????????.")
    @Test
    void registerWithInValidDiscountRegisterData() throws Exception {
        mockMvc.perform(post("/discounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDiscountRegistrationData)))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("GET /discounts")
    @Nested
    class Describe_get_discounts {

        @DisplayName("????????? ?????????")
        @Nested
        class Context_when_discount_is_exists {
            @BeforeEach
            void setUp() {
                given(discountService.getDiscounts(pageRequest))
                        .willReturn(discountList);
            }

            @DisplayName("?????? ????????? ????????????")
            @Test
            void It_responses_discounts() throws Exception {
                mockMvc.perform(get("/discounts"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].id").exists())
                        .andExpect(jsonPath("$[0].flatRate").exists())
                        .andExpect(jsonPath("$[0].productId").exists());
            }
        }

        @DisplayName("????????? ?????????")
        @Nested
        class Context_when_discount_is_not_exists {
            @BeforeEach
            void setUp() {
                given(discountService.getDiscounts(pageRequest))
                        .willReturn(List.of());
            }

            @DisplayName("??? ????????? ????????????")
            @Test
            void It_responses_empty_list() throws Exception {
                mockMvc.perform(get("/discounts"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").isEmpty());
            }
        }
    }

    @DisplayName("GET /discounts/products/{productId}")
    @Nested
    class Describe_get_discounts_products_with_productId {

        @DisplayName("????????? ?????? ???????????? ???????????? ????????? ?????????")
        @Nested
        class Context_when_discount_is_exists {
            @BeforeEach
            void setUp() {
                given(discountService.getDiscountsByProductId(any(Long.class)))
                        .willReturn(discountList);
            }

            @DisplayName("?????? ????????? ????????????")
            @Test
            void It_responses_discounts() throws Exception {
                mockMvc.perform(get("/discounts/products/{productId}", 1L))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(1)))
                        .andExpect(jsonPath("$[0].id").exists())
                        .andExpect(jsonPath("$[0].flatRate").exists())
                        .andExpect(jsonPath("$[0].productId").exists());
            }
        }

        @DisplayName("????????? ?????? ???????????? ???????????? ????????? ?????????")
        @Nested
        class Context_when_discount_is_not_exists {
            @BeforeEach
            void setUp() {
                given(discountService.getDiscountsByProductId(any(Long.class)))
                        .willReturn(List.of());
            }

            @DisplayName("??? ????????? ????????????")
            @Test
            void It_responses_empty_list() throws Exception {
                mockMvc.perform(get("/discounts/products/{productId}", 1L))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").isEmpty());
            }
        }
    }
}
