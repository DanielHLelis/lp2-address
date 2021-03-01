package br.cefetmg.address.cli.view;

import br.cefetmg.address.model.domain.Person;
import br.cefetmg.address.utils.Utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PersonListView extends View {
  private List<Person> people;

  public PersonListView(List<Person> people) {
    this.people = people;
  }

  @Override
  public String render() {
    Map<String, List<Object>> peopleDataFrame = new LinkedHashMap<>();

    peopleDataFrame.put("ID", new ArrayList<>());
    peopleDataFrame.put("Primeiro Nome", new ArrayList<>());
    peopleDataFrame.put("Sobrenome", new ArrayList<>());
    peopleDataFrame.put("Rua", new ArrayList<>());
    peopleDataFrame.put("Código Postal", new ArrayList<>());
    peopleDataFrame.put("Cidade", new ArrayList<>());
    peopleDataFrame.put("Aniversário", new ArrayList<>());

    for (var p : people) {
      peopleDataFrame.get("ID").add(Utils.stringOr(p.getId(), "NULL"));
      peopleDataFrame.get("Primeiro Nome").add(Utils.stringOr(p.getFirstName(), "NULL"));
      peopleDataFrame.get("Sobrenome").add(Utils.stringOr(p.getLastName(), "NULL"));
      peopleDataFrame.get("Rua").add(Utils.stringOr(p.getStreet(), "NULL"));
      peopleDataFrame.get("Código Postal").add(Utils.stringOr(p.getPostalCode(), "NULL"));
      peopleDataFrame.get("Cidade").add(Utils.stringOr(p.getCity(), "NULL"));
      peopleDataFrame.get("Aniversário").add(Utils.stringOr(p.getBirthday(), "NULL"));
    }

    return Utils.tablify(peopleDataFrame);
  }
}
