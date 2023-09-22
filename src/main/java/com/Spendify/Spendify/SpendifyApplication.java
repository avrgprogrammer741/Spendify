package com.Spendify.Spendify;

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
    CommandLineRunner commandLineRunner(UserRepository userRepository, InvoiceRepository invoiceRepository){
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
                   66.0,
                   55.0,
                   55.0
            );
            invoiceRepository.save(invoice);
        };
    }
}
