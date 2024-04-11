package cz.ememsoft.mirko.service.service;

import cz.ememsoft.mirko.service.api.BankAccountResponse;
import cz.ememsoft.mirko.service.domain.BankAccount;
import cz.ememsoft.mirko.service.mapper.BankAccountMapper;
import cz.ememsoft.mirko.service.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final BankAccountMapper bankAccountMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<BankAccountResponse> findAll(Pageable pageable) {
        return bankAccountRepository.findAll(pageable).map(bankAccountMapper::map);
    }

    @Override
    @Transactional
    public void applyForLoan(Long subjectId) throws IllegalStateException {
        Optional<BankAccount> bankAccount = bankAccountRepository.findBySubject_Id(subjectId);
        if (bankAccount.isPresent()) {
            bankAccount.get().setApplyForLoan(true);
            if (bankAccount.get().getBalance().compareTo(BigDecimal.TEN) <= 0) {
                throw new IllegalStateException("Your request will be probably rejected due to low balance");
            }
        }
    }
}
