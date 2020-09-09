package br.cefetmg.address.CLI;

import br.cefetmg.address.persist.PersistException;
import br.cefetmg.address.persist.PersonDBModel;

public class DeletarPessoaApp implements CLIApp{

    @Override
    public void run(MainApp main, String[] params) {
        Long id = Long.parseLong(main.query("Id:"));
        
        try {
            PersonDBModel pdbm = new PersonDBModel();
            pdbm.delete(id);
        } catch(PersistException ex){
            System.out.println(ex.getStackTrace());
        }
    }
    
}
