package com.mknieszner.reflectiondemo.xml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeyMultiValueInput {
    private String key;
    private String val1;
    private String val2;
    IconsInput icons1;
    IconsInput icons2;
}
