package com.kurly.promotion.application;

import com.kurly.promotion.domain.Discount;
import com.kurly.promotion.infra.DiscountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DiscountServiceTest {
    static final Long PRODUCT_ID = 1L;
    static final Integer FLAT_RATE = 10;

    private DiscountService discountService;

    private DiscountRepository discountRepository = mock(DiscountRepository.class);

    @BeforeEach
    void setUp() {
        discountService = new DiscountService(discountRepository);
    }

    @DisplayName("registerDiscount는 할인을 등록합니다.")
    @Test
    void registerDiscount() {
        given(discountRepository.save(any(Discount.class)))
                .willReturn(new Discount(1L, FLAT_RATE, PRODUCT_ID));

        Long id = discountService.registerDiscount(FLAT_RATE, PRODUCT_ID);

        assertThat(id).isEqualTo(1L);

        verify(discountRepository).save(any(Discount.class));
    }
}
