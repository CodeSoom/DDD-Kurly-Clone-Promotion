package com.kurly.promotion.application;

import com.kurly.promotion.domain.Discount;
import com.kurly.promotion.infra.DiscountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DiscountServiceTest {
    static final Long PRODUCT_ID = 1L;
    static final Integer FLAT_RATE = 10;

    private Discount discount;

    private DiscountService discountService;

    private DiscountRepository discountRepository = mock(DiscountRepository.class);

    @BeforeEach
    void setUp() {
        this.discount = new Discount(1L, FLAT_RATE, PRODUCT_ID);

        discountService = new DiscountService(discountRepository);
    }

    @DisplayName("registerDiscount는 할인을 등록합니다.")
    @Test
    void registerDiscount() {
        given(discountRepository.save(any(Discount.class)))
                .willReturn(discount);

        Long id = discountService.registerDiscount(FLAT_RATE, PRODUCT_ID);

        assertThat(id).isEqualTo(1L);

        verify(discountRepository).save(any(Discount.class));
    }

    @DisplayName("getDiscounts")
    @Nested
    class Describe_getDiscounts {

        @DisplayName("할인이 있으면")
        @Nested
        class Context_when_discount_is_exists {

            @BeforeEach
            void setUp() {
                given(discountRepository.findAll())
                        .willReturn(List.of(discount));
            }

            @DisplayName("할인 목록을 반환한다")
            @Test
            void it_returns_discounts() {
                List<Discount> discountList = discountService.getDiscounts();

                assertThat(discountList.get(0).getId()).isEqualTo(discount.getId());
            }
        }

        @DisplayName("할인이 없으면")
        @Nested
        class Context_when_discount_is_not_exists {

            @BeforeEach
            void setUp() {
                given(discountRepository.findAll()).willReturn(List.of());
            }

            @DisplayName("빈 목록을 반환한다")
            @Test
            void it_returns_empty_list() {
                List<Discount> discountList = discountService.getDiscounts();

                assertThat(discountList).isEmpty();
            }
        }
    }
}
