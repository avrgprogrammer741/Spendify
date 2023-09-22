package com.Spendify.Spendify.Invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository)
    {
        this.invoiceRepository=invoiceRepository;
    }
    @Autowired
    public List<Invoice> getInvoices(){
        return invoiceRepository.findAll();
    }
//    @Autowired
//    public void addInvoice(Invoice invoice)
//    {
//        boolean exist=invoiceRepository.existsById(invoice.getInvoiceId());
//        if (exist)
//        {
//            throw new IllegalStateException("invoice with this id exist");
//        }
//        invoiceRepository.save(invoice);
//    }
//    @Autowired
//    public void deleteInvoice(Long invoiceId)
//    {
//        boolean exist=invoiceRepository.existsById(invoiceId);
//        if (!exist)
//        {
//            throw new IllegalStateException("invoice with this id exist");
//        }
//        invoiceRepository.deleteById(invoiceId);
//    }

}
