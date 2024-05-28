package cz.ememsoft.micro.service.resource;

import cz.ememsoft.micro.service.exception.InternalTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExHandler {


  @ExceptionHandler(InternalTransactionException.class)
  void doNothing() {

  }
}
