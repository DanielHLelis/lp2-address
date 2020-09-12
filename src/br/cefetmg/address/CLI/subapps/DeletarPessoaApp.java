package br.cefetmg.address.CLI.subapps;

import br.cefetmg.address.CLI.AppIO;
import br.cefetmg.address.CLI.CLIApp;
import br.cefetmg.address.repository.PersonRepository;
import br.cefetmg.address.repository.RepositoryException;

/**
 * CLIApp that removes a person from persistence
 */
public class DeletarPessoaApp implements CLIApp {

  @Override
  public void run(String[] params) {
    AppIO appIO = AppIO.getInstance();

    Long id = Long.parseLong(appIO.query("Id:"));

    try {
      PersonRepository pdbm = new PersonRepository();
      pdbm.delete(id);
    } catch (RepositoryException ex) {
      appIO.getOut().println(ex.getStackTrace());
    }
  }

}
