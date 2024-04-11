package cz.ememsoft.mirko.service.resource;

import cz.ememsoft.mirko.service.exception.InternalTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExHandler {


  @ExceptionHandler(InternalTransactionException.class)
  void doNothing() {

  }
}
