package com.mknieszner.reflectiondemo.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SectionInput {
    private HeaderInput header;
    private KeyValueInput keyValue;
    private KeyMultiValueInput keyMultiValue;
    @JacksonXmlElementWrapper(localName = "dividers")
    @JacksonXmlProperty(localName = "divider")
    private List<Boolean> dividers;
    private TableInput table;

    public static SectionInput header(HeaderInput header) {
        SectionInput section = new SectionInput();
        section.header = header;
        return section;
    }

    public static SectionInput keyValue(KeyValueInput keyValue) {
        SectionInput section = new SectionInput();
        section.keyValue = keyValue;
        return section;
    }

    public static SectionInput keyMultiValue(KeyMultiValueInput keyMultiValue) {
        SectionInput section = new SectionInput();
        section.keyMultiValue = keyMultiValue;
        return section;
    }

    public static SectionInput divider(int count) {
        SectionInput section = new SectionInput();
        List<Boolean> dividers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            dividers.add(true);
        }
        section.dividers = dividers;
        return section;
    }

    public static SectionInput table(TableInput table) {
        SectionInput section = new SectionInput();
        section.table = table;
        return section;
    }
}
