package com.rohanpoc.reportpoc.web;

import com.rohanpoc.reportpoc.reports.ReportResult;
import com.rohanpoc.reportpoc.reports.ThymeleafReportEngine;
import com.rohanpoc.reportpoc.reports.invoice.InvoiceDataService;
import com.rohanpoc.reportpoc.reports.invoice.InvoiceReportTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
public class ReportController {
    private final ThymeleafReportEngine engine;
    private final InvoiceDataService invoiceDataService;
    private final InvoiceReportTemplate invoiceTemplate;

    public ReportController(ThymeleafReportEngine engine,
                            InvoiceDataService invoiceDataService,
                            InvoiceReportTemplate invoiceTemplate) {
        this.engine = engine;
        this.invoiceDataService = invoiceDataService;
        this.invoiceTemplate = invoiceTemplate;
    }

    @GetMapping(value = "/reports/invoice/html", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> invoiceHtml() {
        var invoice = invoiceDataService.sampleInvoice();
        var result = engine.renderHtml(invoiceTemplate, invoice);

        return switch (result) {
            case ReportResult.Success s -> ResponseEntity.ok(s.html());
            case ReportResult.Failure f -> ResponseEntity.internalServerError()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(f.code() + ": " + f.message());
        };
    }

    @GetMapping("/reports/invoice/download")
    public ResponseEntity<byte[]> downloadInvoiceHtml() {
        var invoice = invoiceDataService.sampleInvoice();
        var result = engine.renderHtml(invoiceTemplate, invoice);

        return switch (result) {
            case ReportResult.Success s -> {
                byte[] bytes = s.html().getBytes(StandardCharsets.UTF_8);
                yield ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice-report.html")
                        .contentType(MediaType.TEXT_HTML)
                        .body(bytes);
            }
            case ReportResult.Failure f -> ResponseEntity.internalServerError()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body((f.code() + ": " + f.message()).getBytes(StandardCharsets.UTF_8));
        };
    }
}
