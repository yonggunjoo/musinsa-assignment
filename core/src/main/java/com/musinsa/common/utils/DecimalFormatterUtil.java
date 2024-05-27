package com.musinsa.common.utils;

import java.math.BigInteger;
import java.text.DecimalFormat;

public class DecimalFormatterUtil {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#,###");

    public static String format(BigInteger price) {
        if (price == null) {
            return null;
        }
        return decimalFormat.format(price);
    }
}
