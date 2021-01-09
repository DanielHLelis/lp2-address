package br.cefetmg.address.core.model.domain;

/**
 * data representation and logic
 *
 * @author Daniel H. Lelis
 * @author Ana Luisa
 */
public abstract class Model {
  /**
   * should only be used for logging, shouldn't be used externally
   *
   * @return the model representation for logging
   */
  public abstract String repr();

  /**
   * template method that assigns {@link #repr()} as default toString
   *
   * @return the logging representation
   */
  @Override
  public String toString() {
    return this.repr();
  }
}
