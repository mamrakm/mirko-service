package cz.ememsoft.micro.service.service;

import cz.ememsoft.micro.service.api.PostTransactionRequest;
import cz.ememsoft.micro.service.exception.InternalTransactionException;

public interface TransactionService {

    /**
     * Method will add transaction to the account specified by {@code id} . If Balance of account after the transaction was added is below 200
     * then service <b>MUST</b> throw {@link InternalTransactionException} and such transaction needs to be
     * <b>saved</b> anyway. If amount of transaction is negative then {@link IllegalArgumentException} <b>MUST</b> thrown.
     *
     * @param request holding transaction amount
     * @param account id of account where transaction will be added
     */
    void createTransaction(Long account, PostTransactionRequest request);
}
