package com.kurly.promotion.domain;

import com.kurly.promotion.domain.vo.Period;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Discount 도메인의 입력을 책임
 */
public class DiscountCommand {

    @Builder
    public static class RegisterDiscount{
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final Integer fixedAmount;
        private final Integer flatRate;

        /**
         * 정률 할인 엔티티를 반환한다.
         * @return 정률 할인
         */
        public Discount toFlatRateDiscount() {
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
         * 정액 할인 엔티티를 반환한다.
         * @return 정액 할인
         */
        public Discount toFixedAmountDiscount() {
            Period period = Period.builder()
                    .startDate(startDate)
                    .endDate(endDate)
                    .build();

            return FixedAmountDiscount.builder()
                    .fixedAmount(fixedAmount)
                    .period(period)
                    .build();
        }
    }
}
