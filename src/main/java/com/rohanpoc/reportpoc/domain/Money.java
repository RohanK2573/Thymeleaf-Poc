package com.rohanpoc.reportpoc.domain;

import java.text.NumberFormat;
import java.util.Locale;

public final class Money {
    private Money() { }

    public static String formatRupeesFromPaise(long paise) {
        double rupees = paise / 100.0;
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        return nf.format(rupees);
    }
}
