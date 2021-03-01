package br.cefetmg.address.cli.view;

import br.cefetmg.address.model.domain.Person;

public class UpdatePersonView extends View {
  private Person person;

  public UpdatePersonView(Person person) {
    this.person = person;
  }

  @Override
  public String render() {
    return String.format("%s foi atualizade com sucesso! ID: %d\n", this.person.toString(), this.person.getId());
  }
}
