package cz.ememsoft.demo.mapper;

import cz.ememsoft.demo.api.BankAccountResponse;
import cz.ememsoft.demo.domain.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BankAccountMapper {

  @Mapping(target = "subject", source = "subject.id")
  BankAccountResponse map(BankAccount bankAccount);
}
