package br.cefetmg.address.cli.controller.apps.person;

import br.cefetmg.address.cli.controller.apps.CLIApp;
import br.cefetmg.address.cli.view.PersonView;
import br.cefetmg.address.cli.view.View;
import br.cefetmg.address.core.controller.ControllerException;
import br.cefetmg.address.core.controller.PersonController;
import br.cefetmg.address.core.model.domain.Person;

public class GetPersonApp extends CLIApp {

  @Override
  public String getDescription() {
    return "obter pessoa pelo ID";
  }

  @Override
  public void run() throws ControllerException {
    String id = query("ID").trim();

    Long idLong = Long.parseLong(id);

    PersonController personController = new PersonController();
    Person person = personController.getPersonById(idLong);

    View view = new PersonView(person);
    out.println(view.render());
  }
}
