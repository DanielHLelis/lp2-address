package br.cefetmg.address.CLI.subapps;

import br.cefetmg.address.CLI.CLIApp;
import br.cefetmg.address.CLI.MainApp;
import br.cefetmg.address.models.PersonModel;
import br.cefetmg.address.repository.PersonRepository;
import br.cefetmg.address.repository.RepositoryException;
import br.cefetmg.address.utils.Utils;

import java.time.LocalDate;

public class AtualizarPessoaApp implements CLIApp {
    @Override
    public void run(MainApp main, String[] params) {
        Long id = Long.parseLong(main.query("Id:"));

        try {
            PersonRepository pdbm = new PersonRepository();
            PersonModel person = pdbm.getById(id);

            if (person == null) {
                System.out.println("Pessoa não existe!");
                return;
            }

            person.setFirstName(
                    Utils.stringOr(main.query("Primeiro nome [" + person.getFirstName() + "]:"), person.getFirstName()));
            person.setLastName(Utils.stringOr(main.query("Último nome [" + person.getLastName() + "]:"), person.getLastName()));
            person.setStreet(Utils.stringOr(main.query("Rua [" + person.getStreet() + "]:"), person.getStreet()));
            person.setPostalCode(Integer
                    .parseInt(Utils.stringOr(main.query("CEP [" + person.getPostalCode() + "]:"), person.getPostalCode())));
            person.setCity(Utils.stringOr(main.query("Cidade [" + person.getCity() + "]:"), person.getCity()));
            person.setBirthday(LocalDate.parse(Utils.stringOr(
                    main.query("Nascimento (AAAA-MM-DD) [" + person.getBirthday() + "]:"), person.getBirthday())));

            pdbm.update(person);
        } catch (RepositoryException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

}
