package com.Spendify.Spendify.Invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping(value = InvoiceController.INVOICE_ENDPOINT)
public class InvoiceController {
    public static final String INVOICE_ENDPOINT = "/api/v1/invoice";

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<InvoiceDTO> addInvoice(@Validated @RequestBody InvoiceAddRequestDTO addRequest) {
        InvoiceDTO createdInvoice = invoiceService.addInvoice(addRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInvoice);
    }

    @GetMapping
    public List<InvoiceDTO> getInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("{invoiceId}")
    public InvoiceDTO getInvoice(@PathVariable("invoiceId") Long invoiceId) {
        return invoiceService.getInvoice(invoiceId);
    }

    @DeleteMapping("/{invoiceId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDebt(@PathVariable("invoiceId") Long invoiceId) {
        invoiceService.deleteInvoice(invoiceId);
    }
}
