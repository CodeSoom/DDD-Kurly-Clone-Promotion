package com.kurly.promotion.application;

import com.kurly.promotion.domain.Discount;
import com.kurly.promotion.domain.DiscountCommand;
import com.kurly.promotion.domain.DiscountFactory;
import com.kurly.promotion.domain.DiscountType;
import com.kurly.promotion.domain.FlatRateDiscount;
import com.kurly.promotion.domain.vo.Period;
import com.kurly.promotion.infra.DiscountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DiscountServiceTest {
    private static final Long ID = 1L;
    private static final Long PRODUCT_ID = 10L;

    private DiscountService discountService;
    private DiscountRepository discountRepository = mock(DiscountRepository.class);

    private DiscountCommand.RegisterDiscount registerRequest;
    private Discount createdDiscount;

    private final PageRequest pageRequest = PageRequest.of(0, 10);
    private Integer givenFlatRate;
    private LocalDate givenStartDate;
    private LocalDate givenEndDate;

    @BeforeEach
    void setUp() {
        discountService = new DiscountService(new DiscountFactory(), discountRepository);

        givenStartDate = LocalDate.of(2022, 1, 1);
        givenEndDate = LocalDate.of(2222, 12, 31);
        givenFlatRate = 20;

        registerRequest = DiscountCommand.RegisterDiscount.builder()
                .startDate(givenStartDate)
                .endDate(givenEndDate)
                .flatRate(givenFlatRate)
                .build();

        createdDiscount = FlatRateDiscount.builder()
                .period(new Period(givenStartDate, givenEndDate))
                .flatRate(givenFlatRate)
                .build();
        createdDiscount.apply(PRODUCT_ID);
        ReflectionTestUtils.setField(createdDiscount, "id", ID);
    }

    @DisplayName("registerDiscount는 할인을 등록합니다.")
    @Test
    void registerDiscount() {
        //given
        given(discountRepository.save(any(Discount.class)))
                .willReturn(createdDiscount);

        //when
        Long id = discountService.registerDiscount(registerRequest, PRODUCT_ID, DiscountType.FLAT_RATE);

        //then
        assertThat(id).isEqualTo(ID);
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
                PageImpl page = new PageImpl(
                        List.of(createdDiscount),
                        pageRequest,
                        10
                );

                given(discountRepository.findAll(pageRequest))
                        .willReturn(page);
            }

            @DisplayName("할인 목록을 반환한다")
            @Test
            void it_returns_discounts() {
                List<Discount> discountList = discountService
                        .getDiscounts(pageRequest);

                assertThat(discountList.get(0).getId()).isEqualTo(createdDiscount.getId());
            }
        }

        @DisplayName("할인이 없으면")
        @Nested
        class Context_when_discount_is_not_exists {

            @BeforeEach
            void setUp() {
                given(discountRepository.findAll(pageRequest))
                        .willReturn(Page.empty());
            }

            @DisplayName("빈 목록을 반환한다")
            @Test
            void it_returns_empty_list() {
                List<Discount> discountList = discountService
                        .getDiscounts(pageRequest);

                assertThat(discountList).isEmpty();
            }
        }
    }

    @DisplayName("getDiscountsByProductId")
    @Nested
    class Describe_getDiscountsByProductId {

        @DisplayName("주어진 상품 식별자에 해당하는 할인이 있으면")
        @Nested
        class Context_when_discount_is_exists {

            @BeforeEach
            void setUp() {
                given(discountRepository.findAllByProductId(PRODUCT_ID))
                        .willReturn(List.of(createdDiscount));
            }

            @DisplayName("할인 목록을 반환한다")
            @Test
            void it_returns_discounts() {
                List<Discount> discountList = discountService.getDiscountsByProductId(PRODUCT_ID);

                assertThat(discountList.get(0).getId()).isEqualTo(createdDiscount.getId());
            }
        }

        @DisplayName("주어진 상품 식별자에 해당하는 할인이 없으면")
        @Nested
        class Context_when_discount_is_not_exists {

            @BeforeEach
            void setUp() {
                given(discountRepository.findAllByProductId(PRODUCT_ID))
                        .willReturn(List.of());
            }

            @DisplayName("빈 목록을 반환한다")
            @Test
            void it_returns_empty_list() {
                List<Discount> discountList = discountService.getDiscountsByProductId(PRODUCT_ID);

                assertThat(discountList).isEmpty();
            }
        }
    }
}
