package com.Spendify.Spendify.Invoice.api;

import com.Spendify.Spendify.Invoice.InvoiceAddRequestDTO;
import com.Spendify.Spendify.Invoice.InvoiceDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InvoiceControllerIntegrationTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;
    @BeforeAll
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void deleteInvoice() throws Exception{
        InvoiceDTO invoiceDTO = createInvoice();
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/invoice/{id}", invoiceDTO.invoiceId()))
                .andExpect(status().is2xxSuccessful());

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/invoice/{id}", invoiceDTO.invoiceId()))
                .andExpect(status().isNotFound());
    }
    private InvoiceDTO createInvoice() throws Exception {
        String response = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/invoice")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(
                                InvoiceAddRequestDTO.builder()
                                        .userId(1L)
                                        .exchangeRate(2.0)
                                        .price(2.0)
                                        .currencyId(1L)
                                        .build()
                        ))
                )
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse().getContentAsString();
        InvoiceDTO quiz = objectMapper.readValue(response, InvoiceDTO.class);
        return quiz;
    }
}
