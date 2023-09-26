package com.Spendify.Spendify.Debt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {
}
