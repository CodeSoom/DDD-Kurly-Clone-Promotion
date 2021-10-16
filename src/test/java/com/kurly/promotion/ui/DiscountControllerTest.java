package com.kurly.promotion.ui;

import com.kurly.promotion.application.DiscountService;
import com.kurly.promotion.domain.Discount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DiscountController.class)
class DiscountControllerTest {

    private List<Discount> discountList;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiscountService discountService;

    @BeforeEach
    void setUp() {
        this.discountList = List.of(
                new Discount(1L, 10, 1004L)
        );
    }

    @DisplayName("GET /discounts")
    @Nested
    class Describe_get_discounts {

        @DisplayName("할인이 있으면")
        @Nested
        class Context_when_discount_is_exists {
            @BeforeEach
            void setUp() {
                given(discountService.getDiscounts()).willReturn(discountList);
            }

            @DisplayName("할인 목록을 응답한다")
            @Test
            void It_responses_discounts() throws Exception {
                mockMvc.perform(get("/discounts"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].id").exists())
                        .andExpect(jsonPath("$[0].flatRate").exists())
                        .andExpect(jsonPath("$[0].productId").exists());
            }
        }

        @DisplayName("할인이 없으면")
        @Nested
        class Context_when_discount_is_not_exists {
            @BeforeEach
            void setUp() {
                given(discountService.getDiscounts()).willReturn(List.of());
            }

            @DisplayName("빈 목록을 응답한다")
            @Test
            void It_responses_empty_list() throws Exception {
                mockMvc.perform(get("/discounts"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").isEmpty());
            }
        }
    }
}
