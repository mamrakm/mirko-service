package com.example.demo.service;

import com.example.demo.api.PostTransactionRequest;
import com.example.demo.domain.Transaction;
import com.example.demo.exception.InternalTransactionException;
import com.example.demo.repository.BankAccountRepository;
import com.example.demo.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {
    private BankAccountRepository bankAccountRepository;
    private TransactionRepository transactionRepository;

    @Override
    public void createTransaction(Long id, PostTransactionRequest request) {
        if (null == id) {
            log.warn("id must not be null", new InternalTransactionException());
            return;
        }

        var account = bankAccountRepository.findById(id).orElse(null);
        if (null == account) {
            log.warn("No subject with [id={}] found", id, new InternalTransactionException());
            return;
        }

        var accountBalanceBeforeTxn = account.getBalance();
        if (null == accountBalanceBeforeTxn) {
            log.warn("There is no record matching id [id={}]", id, new InternalTransactionException());
            return;
        }

        var reminder = accountBalanceBeforeTxn.add(request.getAmount());
        if (reminder.compareTo(BigDecimal.ZERO) < 0) {
            throw new InternalTransactionException();
        }
        var txn = new Transaction();

        if (reminder.compareTo(BigDecimal.valueOf(200)) < 0) {
            txn.setAmount(reminder);
            txn.setBankAccount(account);
            transactionRepository.saveAndFlush(txn);
            throw new InternalTransactionException();
        }

        txn.setAmount(reminder);
        txn.setBankAccount(account);
        transactionRepository.saveAndFlush(txn);
    }
}
