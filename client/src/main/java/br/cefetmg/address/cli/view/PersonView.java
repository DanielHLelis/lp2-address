package br.cefetmg.address.cli.view;

import br.cefetmg.address.model.domain.Person;
import br.cefetmg.address.utils.Utils;

public class PersonView extends View {
  private Person person;

  public PersonView(Person person) {
    this.person = person;
  }

  @Override
  public String render() {
    if (person == null) {
      return "Pessoa inexistente\n";
    }

    StringBuilder builder = new StringBuilder();

    builder.append("Pessoa ");
    builder.append(person);
    builder.append(":\n");

    builder.append("\tID: ");
    builder.append(Utils.stringOr(person.getId(), "NULL"));
    builder.append("\n");
    builder.append("\tPrimeiro nome: \"");
    builder.append(Utils.stringOr(person.getFirstName(), "NULL"));
    builder.append("\"\n");
    builder.append("\tSobrenome: \"");
    builder.append(Utils.stringOr(person.getLastName(), "NULL"));
    builder.append("\"\n");
    builder.append("\tRua: \"");
    builder.append(Utils.stringOr(person.getStreet(), "NULL"));
    builder.append("\"\n");
    builder.append("\tCidade: \"");
    builder.append(Utils.stringOr(person.getCity(), "NULL"));
    builder.append("\"\n");
    builder.append("\tCódigo postal: ");
    builder.append(Utils.stringOr(person.getPostalCode(), "NULL"));
    builder.append("\n");
    builder.append("\tAniversário: ");
    builder.append(Utils.stringOr(person.getBirthday(), "NULL"));
    builder.append("\n");

    return builder.toString();
  }
}
