package cz.ememsoft.demo.service;

import cz.ememsoft.demo.api.PostTransactionRequest;
import cz.ememsoft.demo.domain.Transaction;
import cz.ememsoft.demo.exception.InternalTransactionException;
import cz.ememsoft.demo.repository.BankAccountRepository;
import cz.ememsoft.demo.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {
    private BankAccountRepository bankAccountRepository;
    private TransactionRepository transactionRepository;

    @Transactional
    @Override
    public void createTransaction(Long id, PostTransactionRequest request) {
        if (null == id) {
            log.warn("id must not be null.", new InternalTransactionException());
            return;
        }

        var account = bankAccountRepository.findById(id).orElse(null);
        if (null == account) {
            log.warn("No subject with [id={}] found.", id, new InternalTransactionException());
            return;
        }

        var accountBalanceBeforeTxn = account.getBalance();
        if (null == accountBalanceBeforeTxn) {
            log.warn("There is no record matching id [id={}].", id, new InternalTransactionException());
            return;
        }

        var reminder = accountBalanceBeforeTxn.add(request.getAmount());
        if (reminder.compareTo(BigDecimal.ZERO) < 0) {
            log.debug("Reminder is below zero after amount [{}] is added to the balance.", reminder);
            throw new InternalTransactionException();
        }
        var txn = new Transaction();

        if (reminder.compareTo(BigDecimal.valueOf(200)) < 0) {
            log.debug("Transaction [{}] Reminder is less than 200 after requested amount is added to the balance.", txn.getId());
            txn.setAmount(reminder);
            txn.setBankAccount(account);
            transactionRepository.saveAndFlush(txn);
            log.debug("Transaction [{}] with less than 200 balance is saved anyway.", txn.getId());
            throw new InternalTransactionException();
        }

        txn.setAmount(reminder);
        txn.setBankAccount(account);
        transactionRepository.saveAndFlush(txn);
    }
}
