package com.mknieszner.reflectiondemo.xml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class TableInput {
    private String type;
    private String title;
    private TableHeadersInput headers;
    private TableRowsInput rows;

}
