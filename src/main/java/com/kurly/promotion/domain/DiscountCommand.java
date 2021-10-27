package com.kurly.promotion.domain;

import com.kurly.promotion.domain.vo.Period;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Discount 도메인 객체의 생성을 정의
 */
public class DiscountCommand {
    DiscountCommand() { }

    @ToString
    @Getter
    @Builder
    public static class RegisterDiscount{
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final Integer fixedAmout;
        private final Integer flatRate;

        /**
         * 정률 할인을 생성하여 반환한다.
         * @return 정률 할인
         */
        public Discount createFlatRateDiscount() {
            Period period = Period.builder()
                    .startDate(startDate)
                    .endDate(endDate)
                    .build();

            return FlatRateDiscount.builder()
                    .flatRate(flatRate)
                    .period(period)
                    .build();
        }

        /**
         * 정액 할인을 생성하여 반환한다.
         * @return 정액 할인
         */
        public Discount createFixedAmoutDiscount() {
            Period period = Period.builder()
                    .startDate(startDate)
                    .endDate(endDate)
                    .build();

            return FixedAmountDiscount.builder()
                    .fixedAmount(fixedAmout)
                    .period(period)
                    .build();
        }
    }
}
