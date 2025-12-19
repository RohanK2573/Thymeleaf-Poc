package com.rohanpoc.reportpoc.reports.invoice;

import com.rohanpoc.reportpoc.domain.Invoice;
import com.rohanpoc.reportpoc.reports.ReportTemplate;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Map;

@Component
public class InvoiceReportTemplate implements ReportTemplate<Invoice> {
    @Override
    public String templateName() {
        return "invoice-report";
    }

    @Override
    public Map<String, Object> model(Invoice invoice) {
        return Map.of(
                "invoice", invoice,
                "generatedBy", "Spring Boot + Thymeleaf (Java 17)",
                "generatedAt", OffsetDateTime.now()
        );
    }
}
