package com.rohanpoc.reportpoc.domain;

public record LineItem(String name, int qty, long unitPricePaise) {
    public LineItem {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name must be present");
        if (qty <= 0) throw new IllegalArgumentException("qty must be > 0");
        if (unitPricePaise < 0) throw new IllegalArgumentException("unitPricePaise must be >= 0");
    }

    public long lineTotalPaise() {
        return (long) qty * unitPricePaise;
    }

    public String unitPriceFormatted() {
        return Money.formatRupeesFromPaise(unitPricePaise);
    }

    public String lineTotalFormatted() {
        return Money.formatRupeesFromPaise(lineTotalPaise());
    }
}
