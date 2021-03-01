package br.cefetmg.address.cli.view;

public class QueryView extends View {

  private String question;

  public QueryView(String question) {
    this.question = question;
  }

  @Override
  public String render() {
    return this.question + ": ";
  }
}
