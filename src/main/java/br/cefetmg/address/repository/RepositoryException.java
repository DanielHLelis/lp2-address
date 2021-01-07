package br.cefetmg.address.repository;

/**
 * Wrapper exception for any persistence related exception
 */
public class RepositoryException extends Exception {
  public RepositoryException() {
  }

  public RepositoryException(String message) {
    super(message);
  }

  public RepositoryException(String message, Throwable cause) {
    super(message, cause);
  }

  public RepositoryException(Throwable cause) {
    super(cause);
  }

  public RepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
