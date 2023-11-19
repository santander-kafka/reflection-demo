package com.mknieszner.reflectiondemo.xml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableRowInput {
    private String cell1;
    private String cell2;
    private String cell3;
    private String cell4;
    private String cell5;
}
