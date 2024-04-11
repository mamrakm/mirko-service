package cz.ememsoft.mirko.service.service;

import cz.ememsoft.mirko.service.api.BankAccountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BankAccountService {

  Page<BankAccountResponse> findAll(Pageable pageable);
  void applyForLoan(Long subjectId);
}
