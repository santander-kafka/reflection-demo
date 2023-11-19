package com.mknieszner.reflectiondemo.input;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class Schedule {
    private List<ScheduleItem> items = List.of(new ScheduleItem(BigDecimal.TEN, LocalDate.of(2022,10,10), "Opis_test"));
}