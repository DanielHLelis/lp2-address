package br.cefetmg.address.CLI;

import br.cefetmg.address.models.PersonModel;
import br.cefetmg.address.persist.PersistException;
import br.cefetmg.address.persist.PersonDBModel;
import java.util.List;

public class ListarPessoasApp implements CLIApp {

    @Override
    public void run(MainApp main, String[] params) {
        try {
            PersonDBModel pdbm = new PersonDBModel();
            
            List<PersonModel> persons = pdbm.listAll();
            System.out.println(persons);
        } catch (PersistException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

}
