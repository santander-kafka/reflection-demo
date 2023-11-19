package com.mknieszner.reflectiondemo.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntityDto {
    private String name;
    private String regon;
    private String cif;
    private String nip;
}
