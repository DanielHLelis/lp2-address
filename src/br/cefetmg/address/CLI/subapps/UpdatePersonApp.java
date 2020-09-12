package br.cefetmg.address.CLI.subapps;

import br.cefetmg.address.CLI.AppIO;
import br.cefetmg.address.CLI.CLIApp;
import br.cefetmg.address.models.PersonModel;
import br.cefetmg.address.repository.PersonRepository;
import br.cefetmg.address.repository.RepositoryException;
import br.cefetmg.address.utils.Utils;

import java.time.LocalDate;

/**
 * CLIApp to update a person already in persistence
 */
public class UpdatePersonApp implements CLIApp {
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

      person.setFirstName(
              Utils.stringOr(appIO.query("Primeiro nome [" + person.getFirstName() + "]:"), person.getFirstName()));
      person.setLastName(Utils.stringOr(appIO.query("Último nome [" + person.getLastName() + "]:"), person.getLastName()));
      person.setStreet(Utils.stringOr(appIO.query("Rua [" + person.getStreet() + "]:"), person.getStreet()));
      person.setPostalCode(Integer
              .parseInt(Utils.stringOr(appIO.query("CEP [" + person.getPostalCode() + "]:"), person.getPostalCode())));
      person.setCity(Utils.stringOr(appIO.query("Cidade [" + person.getCity() + "]:"), person.getCity()));
      person.setBirthday(LocalDate.parse(Utils.stringOr(
              appIO.query("Nascimento (AAAA-MM-DD) [" + person.getBirthday() + "]:"), person.getBirthday())));

      pdbm.update(person);
    } catch (RepositoryException ex) {
      appIO.getOut().println(ex.getStackTrace());
    }
  }

}
