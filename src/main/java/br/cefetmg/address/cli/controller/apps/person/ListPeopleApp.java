package br.cefetmg.address.cli.controller.apps.person;

import br.cefetmg.address.cli.controller.apps.CLIApp;
import br.cefetmg.address.cli.view.PersonListView;
import br.cefetmg.address.cli.view.View;
import br.cefetmg.address.core.controller.ControllerException;
import br.cefetmg.address.core.controller.PersonController;
import br.cefetmg.address.core.model.domain.Person;

import java.util.List;

public class ListPeopleApp extends CLIApp {

  @Override
  public String getDescription() {
    return "listar pessoas cadastradas";
  }

  @Override
  public void run() throws ControllerException {
    PersonController personController = new PersonController();
    List<Person> people = personController.getAllPeople();

    View view = new PersonListView(people);
    out.println(view.render());
  }
}
