package br.cefetmg.address.model.dao;

/**
 * Wrapper exception for any persistence related exception
 */
public class DaoException extends Exception {
  public DaoException() {
  }

  public DaoException(String message) {
    super(message);
  }

  public DaoException(String message, Throwable cause) {
    super(message, cause);
  }

  public DaoException(Throwable cause) {
    super(cause);
  }
}
