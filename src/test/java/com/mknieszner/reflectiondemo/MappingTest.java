package com.mknieszner.reflectiondemo;

import com.mknieszner.reflectiondemo.input.ExtraProductDto;
import com.mknieszner.reflectiondemo.util.ByPath;
import com.mknieszner.reflectiondemo.util.Mappings;
import com.mknieszner.reflectiondemo.xml.DocumentInput;
import com.mknieszner.reflectiondemo.xml.HeaderInput;
import com.mknieszner.reflectiondemo.xml.HeaderInputType;
import com.mknieszner.reflectiondemo.xml.KeyValueInput;
import com.mknieszner.reflectiondemo.xml.TableHeadersInput;
import com.mknieszner.reflectiondemo.xml.TableInput;
import com.mknieszner.reflectiondemo.xml.TableRowInput;
import com.mknieszner.reflectiondemo.xml.TableRowsInput;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MappingTest {

    @SneakyThrows
    @Test
    void convert() {
        ExtraProductDto product = new ExtraProductDto(BigDecimal.TEN, "PLN");

        DocumentInput documentInput = new DocumentInput();

        documentInput.addHeader(
                HeaderInput.builder().type(HeaderInputType.DEFAULT).value("Header1").build()
        ).addKeyValue(
                KeyValueInput.builder().key("Kwota").value(Mappings.toFixedString(ByPath.getBigDecimal(product, "amount", "value"))).build()
        ).addKeyValue(
                KeyValueInput.builder().key("Waluta").value(ByPath.getString(product, "amount", "currency")).build()
        ).addTable(
                TableInput.builder()
                        .title("HARMONOGRAM_SPŁAT")
                        .type("SCHEDULE")
                        .headers(TableHeadersInput.builder()
                                .header1("Data")
                                .build())
                        .rows(TableRowsInput.builder()
                                .rows(List.of(
                                        TableRowInput.builder().cell1("2022-11-10").build(),
                                        TableRowInput.builder().cell1("2023-11-10").build())
                                ).build())
                        .build()
        );

        assertEquals(getExpected("convert.xml"), documentInput.toXml());
        assertEquals(getExpected("convert.json"), documentInput.toJSON());

        DocumentInput fromJSON = DocumentInput.fromJSONString(getExpected("convert.json"));
        DocumentInput fromXml = DocumentInput.fromXmlString(getExpected("convert.xml"));

        assertEquals(fromXml, fromJSON);
        assertEquals(fromXml, documentInput);
        assertEquals(fromJSON, documentInput);
    }

    @SneakyThrows
    private String getExpected(String fileName) {
        Path file = Path.of("", "src/test/resources").resolve(fileName);
        return StringUtils.trimAllWhitespace(Files.readString(file));
    }
}
