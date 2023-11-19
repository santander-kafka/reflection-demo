package com.mknieszner.reflectiondemo.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtraProductDto extends ProductDto {
    private Schedule schedule;
    private List<Commission> commissions;

    public ExtraProductDto(BigDecimal value, String currency) {
        super(value, currency);
        this.schedule = new Schedule();
        this.commissions = List.of(new Commission(1L, BigDecimal.ZERO, 10.5d), new Commission(2L, BigDecimal.ZERO, 10.5d));
    }

}
