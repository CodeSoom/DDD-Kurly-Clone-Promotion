package com.kurly.promotion.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kurly.promotion.application.DiscountService;
import com.kurly.promotion.domain.Discount;
import com.kurly.promotion.domain.DiscountCommand;
import com.kurly.promotion.domain.DiscountType;
import com.kurly.promotion.domain.FlatRateDiscount;
import com.kurly.promotion.domain.vo.Period;
import com.kurly.promotion.dto.DiscountDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DiscountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DiscountService discountService;

    private DiscountDto.RegisterDiscount requestDiscount;
    private DiscountDto.RegisterDiscount invalidDiscountRegistration;
    private Discount registeredDiscount;

    private final Long registeredId = 1L;
    private final Integer flatRate = 25;
    private final PageRequest pageRequest = PageRequest.of(0, 10);
    private final LocalDate givenStartDate = LocalDate.of(2021,10,25);
    private final LocalDate givenEndDate = LocalDate.of(2221,10,25);
    private List<Discount> discountList;

    @BeforeEach
    void setUp() {
        invalidDiscountRegistration = DiscountDto.RegisterDiscount.builder().build();

        requestDiscount = DiscountDto.RegisterDiscount.builder()
                .productId(1L)
                .startDate(givenStartDate)
                .endDate(givenEndDate)
                .discountType(DiscountType.FLAT_RATE)
                .flatRate(flatRate)
                .build();

        registeredDiscount = FlatRateDiscount.builder()
                .flatRate(flatRate)
                .period(new Period(givenStartDate, givenEndDate))
                .build();
        registeredDiscount.apply(registeredId);

        this.discountList = List.of(
                registeredDiscount
        );
    }

    @DisplayName("POST 요청은 올바른 할인 정보가 주어진다면 상태코드 201 Created 를 응답한다.")
    @Test
    void registerWithValidDiscountRegisterData() throws Exception {
        given(discountService.registerDiscount(any(DiscountCommand.RegisterDiscount.class), any(Long.class), any(DiscountType.class)))
                .willReturn(registeredId);

        mockMvc.perform(post("/discounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDiscount)))
                .andExpect(header().string("location", "/discounts/" + registeredId))
                .andExpect(status().isCreated());
    }

    @DisplayName("POST 요청은 올바르지 않은 할인 정보가 주어진다면 상태코드 400 BadRequest 를 응답한다.")
    @Test
    void registerWithInValidDiscountRegisterData() throws Exception {
        mockMvc.perform(post("/discounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDiscountRegistration)))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("GET /discounts")
    @Nested
    class Describe_get_discounts {

        @DisplayName("할인이 있으면")
        @Nested
        class Context_when_discount_is_exists {
            @BeforeEach
            void setUp() {
                given(discountService.getDiscounts(pageRequest))
                        .willReturn(discountList);
            }

            @DisplayName("할인 목록을 응답한다")
            @Test
            void It_responses_discounts() throws Exception {
                mockMvc.perform(get("/discounts"))
                        .andExpect(status().isOk())
                        //.andExpect(jsonPath("$[0].id").exists())
                        //.andExpect(jsonPath("$[0].flatRate").exists())
                        .andExpect(jsonPath("$[0].productId").exists());
            }
        }

        @DisplayName("할인이 없으면")
        @Nested
        class Context_when_discount_is_not_exists {
            @BeforeEach
            void setUp() {
                given(discountService.getDiscounts(pageRequest))
                        .willReturn(List.of());
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

    @DisplayName("GET /discounts/products/{productId}")
    @Nested
    class Describe_get_discounts_products_with_productId {

        @DisplayName("주어진 상품 식별자에 해당하는 할인이 있으면")
        @Nested
        class Context_when_discount_is_exists {
            @BeforeEach
            void setUp() {
                given(discountService.getDiscountsByProductId(any(Long.class)))
                        .willReturn(discountList);
            }

            @DisplayName("할인 목록을 응답한다")
            @Test
            void It_responses_discounts() throws Exception {
                mockMvc.perform(get("/discounts/products/{productId}", 1L))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(1)))
                        //.andExpect(jsonPath("$[0].id").exists())
                        //.andExpect(jsonPath("$[0].flatRate").exists())
                        .andExpect(jsonPath("$[0].productId").exists());
            }
        }

        @DisplayName("주어진 상품 식별자에 해당하는 할인이 없으면")
        @Nested
        class Context_when_discount_is_not_exists {
            @BeforeEach
            void setUp() {
                given(discountService.getDiscountsByProductId(any(Long.class)))
                        .willReturn(List.of());
            }

            @DisplayName("빈 목록을 응답한다")
            @Test
            void It_responses_empty_list() throws Exception {
                mockMvc.perform(get("/discounts/products/{productId}", 1L))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").isEmpty());
            }
        }
    }
}
