package cz.ememsoft.demo.resource;

import cz.ememsoft.demo.exception.InternalTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExHandler {


  @ExceptionHandler(InternalTransactionException.class)
  void doNothing() {

  }
}
