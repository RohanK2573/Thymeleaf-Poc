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
        var report = invoiceDataService.sampleReport();
        var result = engine.renderHtml(invoiceTemplate, report);

        if (result instanceof ReportResult.Success success) {
            return ResponseEntity.ok(success.html());
        }

        ReportResult.Failure failure = (ReportResult.Failure) result;
        return ResponseEntity.internalServerError()
                .contentType(MediaType.TEXT_PLAIN)
                .body(failure.code() + ": " + failure.message());
    }

    @GetMapping("/reports/invoice/download")
    public ResponseEntity<byte[]> downloadInvoiceHtml() {
        var report = invoiceDataService.sampleReport();
        var result = engine.renderHtml(invoiceTemplate, report);

        if (result instanceof ReportResult.Success success) {
            byte[] bytes = success.html().getBytes(StandardCharsets.UTF_8);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice-report.html")
                    .contentType(MediaType.TEXT_HTML)
                    .body(bytes);
        }

        ReportResult.Failure failure = (ReportResult.Failure) result;
        return ResponseEntity.internalServerError()
                .contentType(MediaType.TEXT_PLAIN)
                .body((failure.code() + ": " + failure.message()).getBytes(StandardCharsets.UTF_8));
    }
}
