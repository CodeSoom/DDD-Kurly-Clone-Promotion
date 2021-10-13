package com.kurly.promotion.application;

import com.kurly.promotion.domain.Discount;
import com.kurly.promotion.dto.DiscountRegisterData;
import com.kurly.promotion.infra.DiscountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DiscountServiceTest {

    private DiscountService discountService;

    private DiscountRepository discountRepository = mock(DiscountRepository.class);

    private DiscountRegisterData discountRegisterData;

    @BeforeEach
    void setUp() {
        discountService = new DiscountService(discountRepository);

        discountRegisterData = DiscountRegisterData.builder()
                .productId(1L)
                .flatRate(25)
                .build();
    }

    @DisplayName("registerDiscount 는 올바른 할인 정보가 주어진다면 할인을 등록한다.")
    @Test
    void registerDiscount() {
        given(discountRepository.save(any(Discount.class)))
                .will(invocation -> invocation.<Discount>getArgument(0));

        discountService.registerDiscount(discountRegisterData);

        verify(discountRepository).save(any(Discount.class));
    }
}
