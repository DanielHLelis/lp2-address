package br.cefetmg.address.controller;

public class ControllerException extends Exception {
  public ControllerException() {
  }

  public ControllerException(String message) {
    super(message);
  }

  public ControllerException(String message, Throwable cause) {
    super(message, cause);
  }

  public ControllerException(Throwable cause) {
    super(cause);
  }
}
