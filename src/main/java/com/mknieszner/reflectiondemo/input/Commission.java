package com.mknieszner.reflectiondemo.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commission {
    private Long id;
    private BigDecimal amount;
    private Double percentage;
}
