package cz.ememsoft.micro.service.api;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PostTransactionRequest {

  @PositiveOrZero
  private BigDecimal amount;
}
