package br.cefetmg.address.cli.controller.apps.person;

import br.cefetmg.address.cli.controller.apps.CLIApp;
import br.cefetmg.address.cli.view.DeletePersonView;
import br.cefetmg.address.cli.view.View;
import br.cefetmg.address.core.controller.ControllerException;
import br.cefetmg.address.core.controller.PersonController;
import br.cefetmg.address.core.model.domain.Person;

public class DeletePersonApp extends CLIApp {

  @Override
  public String getDescription() {
    return "remover pessoa";
  }

  @Override
  public void run() throws ControllerException {
    String id = query("ID").trim();

    Long idLong = Long.parseLong(id);

    PersonController personController = new PersonController();
    Person person = personController.deletePerson(idLong);

    View view = new DeletePersonView(person);
    out.println(view.render());
  }
}
