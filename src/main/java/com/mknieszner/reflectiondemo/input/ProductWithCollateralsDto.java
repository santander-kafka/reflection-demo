package com.mknieszner.reflectiondemo.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductWithCollateralsDto {
    private ProductDto product;
    @Builder.Default
    private List<CollateralDto> collaterals = new ArrayList<>();
}
