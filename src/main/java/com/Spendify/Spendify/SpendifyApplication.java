package com.Spendify.Spendify;


import com.Spendify.Spendify.Currency.CurrencyRepository;
import com.Spendify.Spendify.Debt.DebtRepository;
import com.Spendify.Spendify.Expense.ExpenseRepository;
import com.Spendify.Spendify.Friendship.FriendshipRepository;
import com.Spendify.Spendify.Invoice.InvoiceRepository;
import com.Spendify.Spendify.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SpendifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpendifyApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, ExpenseRepository expenseRepository, InvoiceRepository invoiceRepository, CurrencyRepository currencyRepository, DebtRepository debtRepository, FriendshipRepository friendshipRepository){
        return args -> {
//            User Maciek1 = new User(
//                    "Maciek",
//                    "Kowalski",
//                    "admin",
//                    "m.krajewa@gmail.com",
//                    null,
//                    true
//            );
//            userRepository.save(Maciek1);
//            User Kuba = new User(
//                    "Kuba",
//                    "Kowal",
//                    "1234",
//                    "k.kowal@gmail.com",
//                    null,
//                    true
//            );
//            userRepository.save(Kuba);
//            long millis = System.currentTimeMillis();
//            Date now = new Date(millis);
//            Friendship friendship =new Friendship(1L,now,Kuba, Maciek1);
//            friendshipRepository.save(friendship);
        };
    }
}