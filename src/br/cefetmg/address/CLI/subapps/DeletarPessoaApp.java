package br.cefetmg.address.CLI.subapps;

import br.cefetmg.address.CLI.CLIApp;
import br.cefetmg.address.CLI.MainApp;
import br.cefetmg.address.repository.RepositoryException;
import br.cefetmg.address.repository.PersonRepository;

public class DeletarPessoaApp implements CLIApp {

    @Override
    public void run(MainApp main, String[] params) {
        Long id = Long.parseLong(main.query("Id:"));

        try {
            PersonRepository pdbm = new PersonRepository();
            pdbm.delete(id);
        } catch (RepositoryException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

}
