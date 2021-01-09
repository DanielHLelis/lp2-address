package br.cefetmg.address.cli.view;

import br.cefetmg.address.core.model.domain.Person;

public class AddPersonView extends View {
  private Person person;

  public AddPersonView(Person person) {
    this.person = person;
  }

  @Override
  public String render() {
    return String.format("%s foi adicionade com sucesso! ID: %d\n", this.person.toString(), this.person.getId());
  }
}
