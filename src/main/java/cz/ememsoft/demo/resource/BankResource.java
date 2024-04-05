package cz.ememsoft.demo.resource;

import cz.ememsoft.demo.api.BankAccountResponse;
import cz.ememsoft.demo.api.PostTransactionRequest;
import cz.ememsoft.demo.service.BankAccountService;
import cz.ememsoft.demo.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class BankResource {

  private final BankAccountService bankAccountService;
  private final TransactionService transactionService;

  @GetMapping
  public ResponseEntity<Page<BankAccountResponse>> getLowBalanceAccounts(Pageable pageable) {
    return ResponseEntity.ok(bankAccountService.findAll(pageable));
  }
  
  @PostMapping("/{id}/transaction")
  public ResponseEntity<Void> addTransaction(@PathVariable Long id, @Valid @RequestBody PostTransactionRequest request, Errors errors) {
    if (errors.hasErrors()) {
      ResponseEntity.badRequest().build();
    }

    transactionService.createTransaction(id, request);
    return ResponseEntity.accepted().build();
  }

  @PostMapping("/{id}/transaction")
  public ResponseEntity<Void> applyForLoan(@PathVariable Long id) {
    try {
      bankAccountService.applyForLoan(id);
    } catch (IllegalStateException e) {
      log.error(e.getMessage());
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.accepted().build();
  }

}
