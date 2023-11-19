package com.mknieszner.reflectiondemo.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Mappings {

    public static String toString(Double data) {
        if (data == null) {
            return null;
        }

        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.GERMAN));
        return decimalFormat.format(data);
    }

    public static String toString(BigDecimal data) {
        if (data == null) {
            return null;
        }

        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.GERMAN));
        return decimalFormat.format(data);
    }


    public static String toString(LocalDate data) {
        if (data == null) {
            return null;
        }

        return data.toString();
    }

    public static String toFixedString(BigDecimal data) {
        if (data == null) {
            return null;
        }

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.GERMAN));
        return decimalFormat.format(data);
    }

    public static List newList() {
        return new ArrayList();
    }



}
