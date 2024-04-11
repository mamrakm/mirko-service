package cz.ememsoft.mirko.service.mapper;

import cz.ememsoft.mirko.service.api.BankAccountResponse;
import cz.ememsoft.mirko.service.domain.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BankAccountMapper {

  @Mapping(target = "subject", source = "subject.id")
  BankAccountResponse map(BankAccount bankAccount);
}
