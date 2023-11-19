package com.mknieszner.reflectiondemo;

import com.mknieszner.reflectiondemo.input.Commission;
import com.mknieszner.reflectiondemo.input.ExtraProductDto;
import com.mknieszner.reflectiondemo.input.ProductDto;
import com.mknieszner.reflectiondemo.util.ByPath;
import com.mknieszner.reflectiondemo.util.Mappings;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ReflectionByPathUtilsTest {

    @Test
    void getString() {

        assertNull(ByPath.getString(new ProductDto(), "amount", "currency"));

        assertEquals(
                "PLN",
                ByPath.getString(new ProductDto(BigDecimal.ONE, "PLN"), "amount", "currency")
        );

        assertEquals(
                "1",
                ByPath.getString(new ProductDto(BigDecimal.ONE, "PLN"), "amount", "value")
        );
    }

    @Test
    void getStringInheritance() {
        assertNull(ByPath.getString(new ExtraProductDto(), "amount", "currency"));

        assertEquals(
                "PLN",
                ByPath.getString(new ExtraProductDto(BigDecimal.ONE, "PLN"), "amount", "currency")
        );

        assertEquals(
                "1",
                ByPath.getString(new ExtraProductDto(BigDecimal.ONE, "PLN"), "amount", "value")
        );
    }

    @Test
    void getStringFromList() {
        assertEquals(
                "10",
                ByPath.getString(new ExtraProductDto(BigDecimal.ONE, "PLN"), "schedule", "items", "0", "amount")
        );
        List<Commission> commissions = (List<Commission>) ByPath.getObject(new ExtraProductDto(BigDecimal.ONE, "PLN"), "commissions");
        assertEquals(2, commissions.size());

        commissions.forEach(commission -> {
            assertEquals(
                    "0",
                    Mappings.toString(
                            ByPath.getBigDecimal(commission, "amount")
                    )
            );
            assertEquals(
                    "10,5",
                    Mappings.toString(
                            ByPath.getDouble(commission, "percentage")
                    )
            );
        });
    }
}

