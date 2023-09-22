package com.Spendify.Spendify;

import com.Spendify.Spendify.Currency.Currency;
import com.Spendify.Spendify.Currency.CurrencyRepository;
import com.Spendify.Spendify.Expense.Expense;
import com.Spendify.Spendify.Expense.ExpenseRepository;
import com.Spendify.Spendify.Invoice.Invoice;
import com.Spendify.Spendify.Invoice.InvoiceRepository;
import com.Spendify.Spendify.User.User;
import com.Spendify.Spendify.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpendifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpendifyApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, ExpenseRepository expenseRepository, InvoiceRepository invoiceRepository, CurrencyRepository currencyRepository){
        return args -> {
            User Maciek = new User(
                    "Maciek",
                    "Kowalski",
                    "admin",
                    "m.krajew@gmail.com",
                    null,
                    true
            );
            userRepository.save(Maciek);
            Invoice invoice = new Invoice(
                    10.0,
                    10.0,
                    10.0
            );
            invoiceRepository.save(invoice);
            Currency dollar = new Currency(
                    "dollar"
            );
            currencyRepository.save(dollar);
            Expense drinks = new Expense(
                    200.0,
                    dollar
            );
            expenseRepository.save(drinks);
        };
    }
}
