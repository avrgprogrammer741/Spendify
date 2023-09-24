package com.Spendify.Spendify.Debt;

import com.Spendify.Spendify.Friendship.Friendship;
import com.Spendify.Spendify.User.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Optional<Debt> getUserDebt(Long userId) {
        return debtRepository.findByUserId(userId);
    }

    public void deleteDebt(User user) throws Exception {
        Optional<Debt> debt = debtRepository.findByUserId(user.getId());
        if (debt.isPresent()) {
            Debt foundDebt = debt.get();
            debtRepository.delete(foundDebt);
        } else {
            throw new Exception("Error");
        }

    }

    public void setDebt(Debt debt) {
        debtRepository.save(debt);
    }
}
