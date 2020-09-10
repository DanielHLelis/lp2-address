package br.cefetmg.address.CLI.subapps;

import br.cefetmg.address.CLI.CLIApp;
import br.cefetmg.address.CLI.MainApp;
import br.cefetmg.address.models.PersonModel;
import br.cefetmg.address.repository.RepositoryException;
import br.cefetmg.address.repository.PersonRepository;

public class ObterPessoaApp implements CLIApp {

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

            System.out.println(person);
        } catch (RepositoryException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

}
