package com.Spendify.Spendify.Balance.api;

import com.Spendify.Spendify.Balance.BalanceAddRequest;
import com.Spendify.Spendify.Balance.BalanceDTO;
import com.Spendify.Spendify.Balance.BalanceUpdateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BalanceControllerIntegrationTest {
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
    public void deleteBalance() throws Exception {
        BalanceDTO balance = createBalance();

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/balances/{id}", balance.id()))
                .andExpect(status().is2xxSuccessful());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/balances/{id}", balance.id()))
                .andExpect(status().isNotFound());
    }

    private BalanceDTO createBalance() throws Exception {
        String response = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/balances")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(
                                BalanceAddRequest.builder()
                                        .currencyId(1L)
                                        .walletId(6L)
                                        .amount(00.01)
                                        .build()
                        ))
                )
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse().getContentAsString();
        BalanceDTO balanceDTO = objectMapper.readValue(response, BalanceDTO.class);
        return balanceDTO;
    }

    @Test
    public void updateBalance() throws Exception {
        Long existingBalanceId = 7L;

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/balances/{id}", existingBalanceId))
                .andExpect(status().isOk());

        BalanceUpdateRequest updatedBalanceData = BalanceUpdateRequest.builder()
                .amount(50.0)
                .build();

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/balances/{id}", existingBalanceId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updatedBalanceData))
                )
                .andExpect(status().is2xxSuccessful());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/balances/{id}", existingBalanceId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", Matchers.equalTo(50.0))); // Verify the updated amount
    }


}
