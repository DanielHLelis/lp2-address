package br.cefetmg.address.CLI.subapps;

import br.cefetmg.address.CLI.AppIO;
import br.cefetmg.address.CLI.CLIApp;
import br.cefetmg.address.models.PersonModel;
import br.cefetmg.address.repository.PersonRepository;
import br.cefetmg.address.repository.RepositoryException;

import java.time.LocalDate;

/**
 * CLIApp to add a person to persistence
 */
public class AddPersonApp implements CLIApp {

  @Override
  public void run(String[] params) {
    PersonModel person = new PersonModel();

    AppIO appIO = AppIO.getInstance();

    person.setFirstName(appIO.query("Primeiro nome:"));
    person.setLastName(appIO.query("Último nome:"));
    person.setStreet(appIO.query("Rua:"));
    person.setPostalCode(Integer.parseInt(appIO.query("CEP:")));
    person.setCity(appIO.query("Cidade:"));
    person.setBirthday(LocalDate.parse(appIO.query("Nascimento (formato ISO):")));

    try {
      PersonRepository pdbm = new PersonRepository();
      pdbm.insert(person);
    } catch (RepositoryException ex) {
      appIO.getOut().println(ex.getStackTrace());
    }
  }

}
