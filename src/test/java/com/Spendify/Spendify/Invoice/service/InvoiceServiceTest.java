package com.Spendify.Spendify.Invoice.service;

import com.Spendify.Spendify.Invoice.Invoice;
import com.Spendify.Spendify.Invoice.InvoiceRepository;
import com.Spendify.Spendify.Invoice.InvoiceService;
import com.Spendify.Spendify.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceTest {
    @Mock
    InvoiceRepository invoiceRepository;

    @InjectMocks
    InvoiceService invoiceService;

    @BeforeAll
    static void beforeAll() {

    }

    @Test
    void testGetInvoice() {
    }

    @Test
    public void shouldCallDeleteWithCorrectInvoice() {
        // given
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();

        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        // when
        invoiceService.deleteInvoice(invoiceId);

        // then
        verify(invoiceRepository, times(1)).delete(invoice);
    }

    @Test
    public void shouldThrowResourceNotFoundExceptionWhenInvoiceNotFound() {
        // given
        Long invoiceId = 6L;

        // when
        Throwable exception = assertThrows(ResourceNotFoundException.class, () -> {
            invoiceService.deleteInvoice(invoiceId);
        });

        // then
        assertEquals("Invoice with ID [6] not found", exception.getMessage());
    }


    @Test
    void testAddInvoice() {
    }
}
