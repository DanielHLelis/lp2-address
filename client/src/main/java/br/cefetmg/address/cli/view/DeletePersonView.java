package br.cefetmg.address.cli.view;

import br.cefetmg.address.model.domain.Person;

public class DeletePersonView extends View {
  private Person person;

  public DeletePersonView(Person person) {
    this.person = person;
  }

  @Override
  public String render() {
    StringBuilder builder = new StringBuilder();

    builder.append("Pessoa ");
    builder.append(person);
    builder.append(" deletada com sucesso!\n");

    return builder.toString();
  }
}
