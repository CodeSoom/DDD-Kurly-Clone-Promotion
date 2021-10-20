package com.kurly.coupon.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class PeriodTest {

    @DisplayName("시작일과 종료일로 기간을 생성할 수 있다.")
    @Test
    void create() {
        // given
        final LocalDate startDate = LocalDate.of(2030, 1, 1);
        final LocalDate endDate = LocalDate.of(2030, 12, 31);
        final Period period = Period.createPeriod(startDate, endDate);

        // then
        assertThat(period).isNotNull();
    }

    @DisplayName("시작일이 종료일보다 이후면 예외를 던진다.")
    @Test
    void start_date_after_end_date() {
        // given
        final LocalDate startDate = LocalDate.of(2050, 1, 1);
        final LocalDate endDate = LocalDate.of(2030, 12, 31);

        assertThatCode(() -> Period.createPeriod(startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("종료일이 시작일 이전이면 예외를 던진다.")
    @Test
    void end_date_before_start_date() {
        // given
        final LocalDate startDate = LocalDate.of(2030, 1, 1);
        final LocalDate endDate = LocalDate.of(2000, 12, 31);


        assertThatCode(() -> Period.createPeriod(startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("빈 값 입력시 예외를 던진다.")
    @Test
    void create_with_null_period() {
        assertThatCode(() -> Period.createPeriod(null, null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
