package com.rohanpoc.reportpoc.reports.invoice;

import java.util.List;

/**
 * View-model for the multi-page invoice report. Everything is already formatted for display
 * so it can be populated from any data source (DB, service, etc.) without binding to a specific schema.
 */
public record InvoiceReportData(
        Header header,
        SummaryOverall summaryOverall,
        SummaryByPortfolio summaryByPortfolio,
        CashReinvest cashReinvest,
        Footer footer
) {
    public record Header(
            String invoiceNo,
            String periodStart,
            String periodEnd,
            String logoDataUri,
            String deskName,
            String deskAddr1,
            String deskAddr2,
            String frontPhone,
            String frontFax,
            String opsPhone,
            String opsFax,
            String clientName,
            String clientAddress,
            String clientContact,
            String clientFax,
            String clientEmail
    ) { }

    public record SummaryOverall(
            String title,
            String currency,
            String totalLendingFees,
            String totalRebates,
            String totalCashReinvest,
            String totalRevenues,
            String splitSg,
            String clientShortName,
            String splitClient
    ) { }

    public record SummaryByPortfolio(
            String title,
            String currency,
            List<PortfolioRow> rows
    ) { }

    public record PortfolioRow(
            String portfolioName,
            String lendingFees,
            String rebates,
            String cashReinvestRevenue,
            String totalRevenues,
            String splitSg,
            String splitClient
    ) { }

    public record CashReinvest(List<CashReinvestRow> rows) { }

    public record CashReinvestRow(
            String date,
            String stockName,
            String isin,
            String reinvestedCash,
            String nominalPerformance,
            String revenue
    ) { }

    public record Footer(
            String leftLine1,
            String leftLine2,
            String leftLine3,
            String printDate,
            String rightLine1,
            String rightLine2,
            String rightLine3
    ) { }
}
