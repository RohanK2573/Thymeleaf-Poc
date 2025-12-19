package com.rohanpoc.reportpoc.domain;

import java.time.LocalDate;
import java.util.List;

public record Invoice(String invoiceNo, String customerName, LocalDate date, List<LineItem> items) {
    public Invoice {
        if (invoiceNo == null || invoiceNo.isBlank()) throw new IllegalArgumentException("invoiceNo must be present");
        if (customerName == null || customerName.isBlank()) throw new IllegalArgumentException("customerName must be present");
        if (date == null) throw new IllegalArgumentException("date must be present");
        if (items == null || items.isEmpty()) throw new IllegalArgumentException("items must be present");
    }

    public long grandTotalPaise() {
        return items.stream().mapToLong(LineItem::lineTotalPaise).sum();
    }

    public String grandTotalFormatted() {
        return Money.formatRupeesFromPaise(grandTotalPaise());
    }
}
