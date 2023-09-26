package com.Spendify.Spendify.Invoice;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class InvoiceDTOMapper implements Function<Invoice, InvoiceDTO> {
    @Override
    public InvoiceDTO apply(Invoice invoice)
    {
        return new InvoiceDTO(
                invoice.getInvoiceId(),
                invoice.getDate(),
                invoice.getPrice(),
                invoice.getExchangeRate(),
                invoice.getCurrency().getId()
        );
    }
}
