package br.cefetmg.address.CLI.subapps;

import br.cefetmg.address.CLI.AppIO;
import br.cefetmg.address.CLI.CLIApp;
import br.cefetmg.address.models.PersonModel;
import br.cefetmg.address.repository.PersonRepository;
import br.cefetmg.address.repository.RepositoryException;

public class ObterPessoaApp implements CLIApp {

  @Override
  public void run(String[] params) {
    AppIO appIO = AppIO.getInstance();

    Long id = Long.parseLong(appIO.query("Id:"));

    try {
      PersonRepository pdbm = new PersonRepository();
      PersonModel person = pdbm.getById(id);

      if (person == null) {
        appIO.getOut().println("Pessoa não existe!");
        return;
      }

      appIO.getOut().println(person);
    } catch (RepositoryException ex) {
      appIO.getOut().println(ex.getStackTrace());
    }
  }

}
