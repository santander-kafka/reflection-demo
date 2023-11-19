package com.mknieszner.reflectiondemo.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TableRowsInput {
    @JacksonXmlElementWrapper(localName = "rows")
    @JacksonXmlProperty(localName = "row")
    @Builder.Default
    private List<TableRowInput> rows = new ArrayList<>();
}
