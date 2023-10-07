package com.Spendify.Spendify.Expense.api;

import com.Spendify.Spendify.Balance.BalanceAddRequest;
import com.Spendify.Spendify.Expense.ExpenseAddRequest;
import com.Spendify.Spendify.Expense.ExpenseUpdateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
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
public class ExpenseControllerIntegrationTest {
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
    public void updateExpense() throws Exception {
        Long expenseId = 1L;

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/expenses/{id}", expenseId))
                .andExpect(status().isOk());

        ExpenseUpdateRequest expenseUpdateRequest = ExpenseUpdateRequest.builder().amountLeft(69.69).build();

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/expenses/{id}", expenseId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(expenseUpdateRequest)))
                .andExpect(status().is2xxSuccessful());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/expenses/{id}", expenseId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amountLeft", Matchers.equalTo(69.69)));
    }
}
