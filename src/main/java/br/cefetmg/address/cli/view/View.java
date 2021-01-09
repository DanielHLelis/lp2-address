package br.cefetmg.address.cli.view;

public abstract class View {
  public abstract String render();

  @Override
  public String toString() {
    return this.render();
  }
}
