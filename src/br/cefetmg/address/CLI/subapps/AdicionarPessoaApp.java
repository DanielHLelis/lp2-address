package br.cefetmg.address.CLI.subapps;

import br.cefetmg.address.CLI.CLIApp;
import br.cefetmg.address.CLI.MainApp;
import br.cefetmg.address.models.PersonModel;
import br.cefetmg.address.repository.RepositoryException;
import br.cefetmg.address.repository.PersonRepository;

import java.time.LocalDate;

public class AdicionarPessoaApp implements CLIApp {

    @Override
    public void run(MainApp main, String[] params) {
        PersonModel person = new PersonModel();
        person.setFirstName(main.query("Primeiro nome:"));
        person.setLastName(main.query("Último nome:"));
        person.setStreet(main.query("Rua:"));
        person.setPostalCode(Integer.parseInt(main.query("CEP:")));
        person.setCity(main.query("Cidade:"));
        person.setBirthday(LocalDate.parse(main.query("Nascimento (formato ISO):")));

        try {
            PersonRepository pdbm = new PersonRepository();
            pdbm.insert(person);
        } catch (RepositoryException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

}
