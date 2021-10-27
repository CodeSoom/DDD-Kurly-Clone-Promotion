package com.kurly.promotion.application;

import com.kurly.promotion.domain.Discount;
import com.kurly.promotion.domain.DiscountCommand;
import com.kurly.promotion.domain.DiscountFactory;
import com.kurly.promotion.domain.DiscountType;
import com.kurly.promotion.domain.FlatRateDiscount;
import com.kurly.promotion.domain.vo.Period;
import com.kurly.promotion.dto.DiscountRegistrationData;
import com.kurly.promotion.infra.DiscountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DiscountServiceTest {
    private static final Long PRODUCT_ID = 1L;
    private static final Integer FLAT_RATE = 10;

    private DiscountCommand.RegisterDiscount registerDiscount;
    private Discount createdDiscount;

    private DiscountService discountService;

    private DiscountRepository discountRepository = mock(DiscountRepository.class);

    @BeforeEach
    void setUp() {
        discountService = new DiscountService(new DiscountFactory(), discountRepository);

        registerDiscount = DiscountCommand.RegisterDiscount.builder()
                .startDate(LocalDate.of(2030,10,25))
                .endDate(LocalDate.of(2230,10,25))
                .flatRate(20)
                .build();

        createdDiscount = FlatRateDiscount.builder()
                .flatRate(20)
                .period(new Period())
                .build();
        ReflectionTestUtils.setField(createdDiscount, "id", 1L);
    }

    @DisplayName("registerDiscount는 할인을 등록합니다.")
    @Test
    void registerDiscount() {
        //given
        given(discountRepository.save(any(Discount.class)))
                .willReturn(createdDiscount);

        //when
        Long id = discountService.registerDiscount(registerDiscount, PRODUCT_ID, DiscountType.FLAT_RATE);

        //then
        assertThat(id).isEqualTo(1L);
        verify(discountRepository).save(any(Discount.class));
    }
}
