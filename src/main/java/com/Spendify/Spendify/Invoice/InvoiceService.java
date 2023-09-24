package com.Spendify.Spendify.Invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceDTOMapper invoiceDTOMapper;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceDTOMapper invoiceDTOMapper)
    {
        this.invoiceRepository=invoiceRepository;
        this.invoiceDTOMapper = invoiceDTOMapper;
    }
    public List<InvoiceDTO> getAllInvoices(){
        return invoiceRepository.findAll().stream()
                .map(invoiceDTOMapper)
                .collect(Collectors.toList());
    }


    public void deleteInvoice(Long invoiceId) throws Exception {
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
        if (invoice.isPresent()) {
            Invoice foundInvoice = invoice.get();
            invoiceRepository.delete(foundInvoice);
        } else {
            throw new Exception("Error");
        }
    }

    public void addInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

//    public Optional<Invoice> getUserInvoice(Long userId) throws Exception {
//        Optional<Invoice> invoice = invoiceRepository.findByUser(userId);
//        if (invoice.isPresent()) {
//            Invoice foundInvoice = invoice.get();
//            return Optional.of(foundInvoice);
//        } else {
//            throw new Exception("Error");
//        }
//    }
}
