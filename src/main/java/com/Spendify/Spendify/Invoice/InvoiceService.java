package com.Spendify.Spendify.Invoice;

import com.Spendify.Spendify.Currency.Currency;
import com.Spendify.Spendify.Currency.CurrencyRepository;
import com.Spendify.Spendify.User.User;
import com.Spendify.Spendify.User.UserRepository;
import com.Spendify.Spendify.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceDTOMapper invoiceDTOMapper;
    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceDTOMapper invoiceDTOMapper, CurrencyRepository currencyRepository, UserRepository userRepository)
    {
        this.invoiceRepository=invoiceRepository;
        this.invoiceDTOMapper = invoiceDTOMapper;
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
    }
    public List<InvoiceDTO> getAllInvoices(){
        return invoiceRepository.findAll().stream()
                .map(invoiceDTOMapper)
                .collect(Collectors.toList());
    }


    public InvoiceDTO getInvoice(Long invoiceId) {
        return invoiceRepository
                .findById(invoiceId)
                .map(invoiceDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice with ID [%s] not found".formatted(invoiceId)));
    }

    public void deleteInvoice(Long invoiceId) {
        Invoice invoice=invoiceRepository.findById(invoiceId).orElseThrow(() -> new ResourceNotFoundException("Invoice with ID [%s] not found".formatted(invoiceId)));
        invoiceRepository.delete(invoice);
    }

    public void addInvoice(InvoiceAddRequest addRequest) {
        Currency currency=currencyRepository.findById(addRequest.currencyId()).orElseThrow(() -> new ResourceNotFoundException(" Currency with ID [%s] not found".formatted(addRequest.currencyId())));
        User user=userRepository.findById(addRequest.userId()).orElseThrow(() -> new ResourceNotFoundException(" User with ID [%s] not found".formatted(addRequest.userId())));
        Invoice invoice=new Invoice();
        invoice.setDate(new Date());
        invoice.setCurrency(currency);
        invoice.setPrice(addRequest.price());
        invoice.setExchangeRate(addRequest.exchangeRate());
        invoice.setUser(user);
        invoiceRepository.save(invoice);


    }
}
