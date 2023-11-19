package com.mknieszner.reflectiondemo.facade;

import com.mknieszner.reflectiondemo.input.BaseEntityDto;
import com.mknieszner.reflectiondemo.input.CustomerDto;
import com.mknieszner.reflectiondemo.input.ExtraProductDto;
import com.mknieszner.reflectiondemo.input.InputWrapper;
import com.mknieszner.reflectiondemo.input.ProductWithCollateralsDto;
import com.mknieszner.reflectiondemo.xml.DocumentInput;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConversionServiceTest {

    @Test
    void getXml() {
        ConversionService sut = new ConversionService();
        DocumentInput result = sut.getXml(InputWrapper.builder()
                .customers(List.of(CustomerDto.builder()
                        .base(BaseEntityDto.builder()
                                .name("Test_name")
                                .cif("12345678")
                                .regon("12345")
                                .nip("123123")
                                .build())
                        .products(
                                List.of(
                                        ProductWithCollateralsDto.builder()
                                                .product(new ExtraProductDto(BigDecimal.ONE, "PLN")).build(),
                                        ProductWithCollateralsDto.builder()
                                                .product(new ExtraProductDto(BigDecimal.TEN, "EUR")).build()
                                )).build()
                )).build());
        assertEquals(getExpected("full.xml"), result.toXml());
    }

    @SneakyThrows
    private String getExpected(String fileName) {
        Path file = Path.of("", "src/test/resources").resolve(fileName);
        return StringUtils.trimAllWhitespace(Files.readString(file));
    }
}