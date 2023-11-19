package com.mknieszner.reflectiondemo.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@NoArgsConstructor
@Data
@AllArgsConstructor
public class ProductDto {
    private String type = "One";
    private ProductDto.Money amount;

    public ProductDto(BigDecimal value, String currency) {
        this.amount = new ProductDto.Money(value, currency);
    }

    @AllArgsConstructor
    static class Money {
        private BigDecimal value;
        private String currency;
    }
}
