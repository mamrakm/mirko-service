package cz.ememsoft.micro.service.mapper;

import cz.ememsoft.micro.service.api.BankAccountResponse;
import cz.ememsoft.micro.service.domain.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BankAccountMapper {

  @Mapping(target = "subject", source = "subject.id")
  BankAccountResponse map(BankAccount bankAccount);
}
