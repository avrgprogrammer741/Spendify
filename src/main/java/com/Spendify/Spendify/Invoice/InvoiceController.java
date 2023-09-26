package com.Spendify.Spendify.Invoice;

import java.util.List;

import com.Spendify.Spendify.Debt.DebtAddRequest;
import com.Spendify.Spendify.Debt.DebtDTO;
import com.Spendify.Spendify.Debt.DebtUpdateRequest;
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
    @PostMapping
    public void addInvoice(@RequestBody InvoiceAddRequest addRequest) {
        invoiceService.addInvoice(addRequest);
    }
}
