package cz.ememsoft.micro.service.repository;

import cz.ememsoft.micro.service.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long>, JpaSpecificationExecutor<BankAccount> {

  @Query("SELECT COUNT(ba) FROM BankAccount ba WHERE ba.subject.id = :subject")
  int numberOfAccounts(Long subject);

  //  @Query("SELECT ba FROM BankAccount ba WHERE ba.subject.id = :subjectId")
  Optional<BankAccount> findBySubject_Id(Long subjectId);

}
