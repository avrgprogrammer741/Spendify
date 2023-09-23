package com.Spendify.Spendify.Debt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DebtService {
    private final DebtRepository debtRepository;
    private final DebtDTOMapper debtDTOMapper;
    public List<DebtDTO> getAllDebts() {
        return debtRepository.findAll()
                .stream()
                .map(debtDTOMapper)
                .collect(Collectors.toList());

    }
    public DebtService(DebtRepository debtRepository, DebtDTOMapper debtDTOMapper) {
        this.debtRepository = debtRepository;
        this.debtDTOMapper = debtDTOMapper;
    }
}
