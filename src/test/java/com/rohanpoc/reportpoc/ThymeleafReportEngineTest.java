package com.rohanpoc.reportpoc;

import com.rohanpoc.reportpoc.reports.ReportResult;
import com.rohanpoc.reportpoc.reports.ThymeleafReportEngine;
import com.rohanpoc.reportpoc.reports.invoice.InvoiceDataService;
import com.rohanpoc.reportpoc.reports.invoice.InvoiceReportTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ThymeleafReportEngineTest {

    @Autowired ThymeleafReportEngine engine;
    @Autowired InvoiceReportTemplate template;
    @Autowired InvoiceDataService dataService;

//    @Test
//    void rendersInvoiceHtml() {
//        var invoice = dataService.sampleInvoice();
//        var result = engine.renderHtml(template, invoice);
//
//        assertThat(result).isInstanceOf(ReportResult.Success.class);
//
//        var html = ((ReportResult.Success) result).html();
//        assertThat(html).contains("Invoice Report");
//        assertThat(html).contains(invoice.invoiceNo());
//        assertThat(html).contains(invoice.customerName());
//    }
}
