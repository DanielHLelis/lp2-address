package br.cefetmg.address.CLI;

import br.cefetmg.address.models.PersonModel;
import br.cefetmg.address.persist.PersistException;
import br.cefetmg.address.persist.PersonDBModel;

public class ObterPessoaApp implements CLIApp {

    @Override
    public void run(MainApp main, String[] params) {
        Long id = Long.parseLong(main.query("Id:"));

        try {
            PersonDBModel pdbm = new PersonDBModel();
            PersonModel person = pdbm.getById(id);

            if (person == null) {
                System.out.println("Pessoa não existe!");
                return;
            }

            System.out.println(person);
        } catch (PersistException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

}
