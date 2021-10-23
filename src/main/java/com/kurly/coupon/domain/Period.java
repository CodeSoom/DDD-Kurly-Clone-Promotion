package com.kurly.coupon.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * 기간.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Period {
    private static final String START_DATE_AFTER_END_DATE_EXCEPTION = "시작일보다 종료일이 이후가 될 수 없습니다.";
    private static final String NULL_NOT_ALLOWED = "빈값이 될 수 없습니다";

    /**
     * 시작일.
     */
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    /**
     * 만료일.
     */
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    private Period(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Period createPeriod(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException(NULL_NOT_ALLOWED);
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(START_DATE_AFTER_END_DATE_EXCEPTION);
        }
        return new Period(startDate, endDate);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Period)) return false;
        Period period = (Period) o;
        return Objects.equals(getStartDate(), period.getStartDate()) && Objects.equals(getEndDate(), period.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartDate(), getEndDate());
    }
}
