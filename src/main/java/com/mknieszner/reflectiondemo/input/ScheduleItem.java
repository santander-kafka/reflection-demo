package com.mknieszner.reflectiondemo.input;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleItem {
    private BigDecimal amount;
    private LocalDate date;
    private String description;
}