package com.mknieszner.reflectiondemo.xml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TableHeadersInput {
    private String header1;
    private String header2;
    private String header3;
    private String header4;
    private String header5;

}
