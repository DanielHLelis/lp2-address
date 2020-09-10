package br.cefetmg.address.models;

public abstract class Model {
  public abstract String repr();

  @Override
  public String toString() {
    return this.repr();
  }
}
