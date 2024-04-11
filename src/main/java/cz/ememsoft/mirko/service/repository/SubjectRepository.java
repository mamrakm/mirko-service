package cz.ememsoft.mirko.service.repository;

import cz.ememsoft.mirko.service.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long>, JpaSpecificationExecutor<Subject> {


  @Query("SELECT ba.subject FROM BankAccount ba WHERE ba.balance < 60")
  List<Subject> getSubjectsWithLowBalance();
}
