package com.kurly.promotion.domain;

import com.kurly.promotion.dto.DiscountDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DiscountDtoMapper {
    DiscountCommand.RegisterDiscount of(DiscountDto.RegisterDiscount requestDiscount);
}
