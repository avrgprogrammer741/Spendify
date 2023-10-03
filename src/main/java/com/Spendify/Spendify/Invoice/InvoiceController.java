package com.Spendify.Spendify.Invoice;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value=InvoiceController.INVOICE_ENDPOINT)
public class InvoiceController {
    public static final String INVOICE_ENDPOINT = "/api/v1/invoice";

    private final InvoiceService invoiceService;
    @PostMapping
    public ResponseEntity<InvoiceDTO> addInvoice(@RequestBody InvoiceAddRequest addRequest) {
        InvoiceDTO createdInvoice = invoiceService.addInvoice(addRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInvoice);
    }

    @Autowired
    public InvoiceController(InvoiceService invoiceService)
    {
        this.invoiceService=invoiceService;
    }

    @GetMapping
    public List<InvoiceDTO> getInvoices()
    {
        return invoiceService.getAllInvoices();
    }
    @GetMapping("{invoiceId}")
    public InvoiceDTO getInvoice(@PathVariable ("invoiceId") Long invoiceId)
    {
        return invoiceService.getInvoice(invoiceId);
    }
    @DeleteMapping("/{invoiceId}")
    public void deleteDebt(@PathVariable("invoiceId") Long invoiceId)
    {
        invoiceService.deleteInvoice(invoiceId);
    }
}
