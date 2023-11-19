package com.mknieszner.reflectiondemo.xml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyValueInput {
    private String key;
    private String value;
    private Boolean strongKey;
    private IconsInput icons;

    public static KeyValueInput keyValue(String key, String value) {
        KeyValueInput result = new KeyValueInput();
        result.key = key;
        result.value = value;
        return result;
    }

    public static KeyValueInput strongKeyValue(String key, String value) {
        KeyValueInput result = new KeyValueInput();
        result.strongKey = true;
        result.key = key;
        result.value = value;
        return result;
    }
}
