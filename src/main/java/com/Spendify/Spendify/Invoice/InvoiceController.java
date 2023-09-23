package com.Spendify.Spendify.Invoice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;
    @Autowired
    public InvoiceController(InvoiceService invoiceService)
    {
        this.invoiceService=invoiceService;
    }
//    @GetMapping
//    public List<InvoiceDTO> getInvoices()
//    {
//        return invoiceService.getAllInvoices();
//    }
//    @DeleteMapping(path = "{invoiceId}")
//    public void deleteInvoice(@PathVariable("invoiceId")Long invoiceId)
//    {
//        invoiceService.deleteInvoice(invoiceId);
//    }
//    @PostMapping
//    public void addNewInvoice(@RequestBody Invoice invoice)
//    {
//        invoiceService.addInvoice(invoice);
//    }

}
