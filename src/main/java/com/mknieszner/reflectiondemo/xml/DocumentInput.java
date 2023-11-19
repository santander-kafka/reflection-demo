package com.mknieszner.reflectiondemo.xml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("Document")
public class DocumentInput {
    @JacksonXmlElementWrapper(localName = "sections")
    @JacksonXmlProperty(localName = "section")
    private List<SectionInput> sections = new ArrayList<>();


    public DocumentInput addHeader(HeaderInput header) {
        sections.add(SectionInput.header(header));
        return this;
    }

    public DocumentInput addKeyValue(KeyValueInput keyValue) {
        sections.add(SectionInput.keyValue(keyValue));
        return this;
    }

    public DocumentInput addKeyMultiValue(KeyMultiValueInput keyMultiValue) {
        sections.add(SectionInput.keyMultiValue(keyMultiValue));
        return this;
    }

    public DocumentInput addDivider(int count) {
        sections.add(SectionInput.divider(count));
        return this;
    }

    public DocumentInput addTable(TableInput table) {
        sections.add(SectionInput.table(table));
        return this;
    }

    @SneakyThrows
    public String toXml() {
        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return xmlMapper.writeValueAsString(this);
    }


    @SneakyThrows
    public static DocumentInput fromXmlString(String value) {
        ObjectMapper jsonMapper = new XmlMapper();
        return jsonMapper.readValue(value, DocumentInput.class);
    }

    @SneakyThrows
    public String toJSON() {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return jsonMapper.writeValueAsString(this);
    }

    @SneakyThrows
    public static DocumentInput fromJSONString(String value) {
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.readValue(value, DocumentInput.class);
    }
}
