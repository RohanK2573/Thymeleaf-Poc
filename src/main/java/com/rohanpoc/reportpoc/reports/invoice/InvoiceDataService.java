package com.rohanpoc.reportpoc.reports.invoice;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

@Service
public class InvoiceDataService {
    private static final String LOGO_SVG = """
            <svg xmlns='http://www.w3.org/2000/svg' width='180' height='40'>
              <rect width='180' height='40' fill='#111'/>
              <rect width='90' height='40' fill='#e30613'/>
              <text x='108' y='26' font-family='Arial' font-size='18' font-weight='700' fill='#fff'>SG</text>
            </svg>
            """;
    private static final String LOGO_DATA_URI = "data:image/svg+xml;base64," +
            Base64.getEncoder().encodeToString(LOGO_SVG.getBytes(StandardCharsets.UTF_8));
    private static final DateTimeFormatter PRINT_DATE_FMT = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy");

    public InvoiceReportData sampleReport() {
        var header = new InvoiceReportData.Header(
                "INV-2025-01",
                "01/01/2025",
                "24/01/2025",
                LOGO_DATA_URI,
                "LMA Securities Lending Desk",
                "17 Cours Valmy",
                "92800 Puteaux",
                "+33 (0)1 5898 7059",
                "+33 (0)1 5305 4754",
                "+33 (0)1 5305 4881",
                "+33 (0)1 5305 4754",
                "EDFFACTU",
                "22-30 avenue de Wagram",
                "EDFFACTU",
                "",
                "contact@edf.com"
        );

        var summaryOverall = new InvoiceReportData.SummaryOverall(
                "INVOICE JANUARY 2025 - OVERALL SUMMARY",
                "EUR",
                "20,500.00",
                "1,900.00",
                "1,100.00",
                "23,500.00",
                "11,750.00",
                "EDF",
                "11,750.00"
        );

        var summaryByPortfolio = new InvoiceReportData.SummaryByPortfolio(
                "INVOICE JANUARY 2025 - SUMMARY BY PORTFOLIO",
                "EUR",
                List.of(
                        new InvoiceReportData.PortfolioRow("LMA Paris", "12,500.00", "1,200.00", "800.00", "14,500.00", "7,250.00", "7,250.00"),
                        new InvoiceReportData.PortfolioRow("LMA London", "8,000.00", "700.00", "300.00", "9,000.00", "4,500.00", "4,500.00")
                )
        );

        var cashReinvest = new InvoiceReportData.CashReinvest(
                List.of(
                        new InvoiceReportData.CashReinvestRow("08/01/2025", "AMUNDI 3M-IC", "FR0007038138", "4,478,060.00", "2.914", "362.49"),
                        new InvoiceReportData.CashReinvestRow("12/01/2025", "SG EQUITY", "FR0007050428", "2,240,000.00", "2.103", "186.64"),
                        new InvoiceReportData.CashReinvestRow("19/01/2025", "LMA CASH FUND", "FR0007050999", "1,980,500.00", "1.944", "102.21")
                )
        );

        var footer = new InvoiceReportData.Footer(
                "Societe Generale : OPER/CLT/DEU/NCI",
                "Societe Anonyme with a share capital of EUR 1 059 459 753, RCS Paris 552 120 222",
                "TVA : FR 27 552 120 222",
                LocalDate.now().format(PRINT_DATE_FMT),
                "Societe Generale",
                "17 Cours Valmy",
                "92800 Puteaux"
        );

        return new InvoiceReportData(header, summaryOverall, summaryByPortfolio, cashReinvest, footer);
    }
}
