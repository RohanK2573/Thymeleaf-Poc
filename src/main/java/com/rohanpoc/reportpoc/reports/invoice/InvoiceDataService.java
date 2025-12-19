package com.rohanpoc.reportpoc.reports.invoice;

import com.rohanpoc.reportpoc.domain.Invoice;
import com.rohanpoc.reportpoc.domain.LineItem;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InvoiceDataService {
    public Invoice sampleInvoice() {
        List<LineItem> items = List.of(
                new LineItem("Laptop", 1, 65000_00),
                new LineItem("Mouse", 2, 500_00),
                new LineItem("Keyboard", 1, 1500_00)
        );

        return new Invoice("INV-101", "Rohan", LocalDate.now(), items);
    }
}
