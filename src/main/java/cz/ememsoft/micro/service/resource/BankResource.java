package cz.ememsoft.micro.service.resource;

import cz.ememsoft.micro.service.api.BankAccountResponse;
import cz.ememsoft.micro.service.api.PostTransactionRequest;
import cz.ememsoft.micro.service.service.BankAccountService;
import cz.ememsoft.micro.service.service.TransactionService;
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

import java.util.List;

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

  @PostMapping("/{id}/transaction/add")
  public ResponseEntity<Void> addTransaction(@PathVariable Long id, @Valid @RequestBody PostTransactionRequest request, Errors errors) {
    if (errors.hasErrors()) {
      ResponseEntity.badRequest().build();
    }

    transactionService.createTransaction(id, request);
    return ResponseEntity.accepted().build();
  }

  @PostMapping("/{id}/loan/apply")
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
