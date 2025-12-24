package com.rohanpoc.reportpoc.reports.invoice;

import com.rohanpoc.reportpoc.reports.ReportTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InvoiceReportTemplate implements ReportTemplate<InvoiceReportData> {
    @Override
    public String templateName() {
        return "invoice/main";
    }

    @Override
    public Map<String, Object> model(InvoiceReportData report) {
        return Map.of("report", report);
    }
}
