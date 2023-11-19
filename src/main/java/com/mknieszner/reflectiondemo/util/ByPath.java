package com.mknieszner.reflectiondemo.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


@Slf4j
public class ByPath {

    public static String getString(Object object, String... path) {
        return ByPath.getString(object, List.of(path));
    }

    public static Double getDouble(Object object, String... path) {
        Object result = ByPath.getObjectByPath(object, List.of(path));
        return result == null ? null : (Double) result;
    }

    public static BigDecimal getBigDecimal(Object object, String... path) {
        return ByPath.getBigDecimal(object, List.of(path));
    }

    public static Object getObject(Object object, String... path) {
        return ByPath.getObjectByPath(object, List.of(path));
    }

    private static BigDecimal getBigDecimal(Object source, List<String> segments) {
        Object result = ByPath.getObjectByPath(source, segments);
        return result == null ? null : (BigDecimal) result;
    }

    private static String getString(Object source, List<String> segments) {
        Object result = ByPath.getObjectByPath(source, segments);
        return result == null ? null : result.toString();
    }

    private static Object getObjectByPath(Object source, List<String> segments) {
        if (source == null) {
            return null;
        }

        if (segments.size() == 0) {
            return source;
        }

        Object result = getObject(source, segments.get(0));

        return ByPath.getObjectByPath(result, segments.subList(1, segments.size()));
    }

    private static Object getObject(Object object, String fieldName) {
        if(object instanceof Collection) {
            return getListItem((Collection<?>) object, fieldName);
        }
        try {
            Field field = getField(object, fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (NullPointerException e) {
            log.info("Null for field: " + fieldName);
            return null;
        } catch (IllegalAccessException e) {
            log.error("Parse exception: ", e);
            throw new RuntimeException(e);
        }
    }

    private static Object getListItem(Collection<?> collection, String index) {
        try {
            return collection.toArray()[Integer.parseInt(index)];
        } catch (NullPointerException e) {
            log.info("Null for index: " + index);
            return null;
        } catch (Exception e) {
            log.error("Parse exception: ", e);
            throw new RuntimeException(e);
        }
    }

    private static Field getField(Object object, String fieldName) {
        for (Class<?> c = object.getClass(); c != null; c = c.getSuperclass()) {
            Field[] fields = c.getDeclaredFields();
            for (Field classField : fields) {
                if (Objects.equals(fieldName, classField.getName())) {
                    return classField;
                }
            }
        }

        return null;
    }
}
