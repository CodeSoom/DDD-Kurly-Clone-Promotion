package com.kurly.coupon.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Name {
    private static final String BLANK_NOT_ALLOWED = "빈값을 입력할 수 없습니다.";
    private String value;

    private Name(String value) {
        this.value = value;
    }

    public static Name of(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(BLANK_NOT_ALLOWED);
        }
        return new Name(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name)) return false;
        Name name = (Name) o;
        return Objects.equals(getValue(), name.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return "Name{" +
                "value='" + value + '\'' +
                '}';
    }
}
