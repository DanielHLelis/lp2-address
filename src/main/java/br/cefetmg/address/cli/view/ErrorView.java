package br.cefetmg.address.cli.view;

public class ErrorView extends View {

  private String message;

  public ErrorView(String message) {
    this.message = message;
  }

  @Override
  public String render() {
    return "Erro: " + this.message + "\n";
  }
}
