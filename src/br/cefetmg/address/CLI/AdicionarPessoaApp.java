package br.cefetmg.address.CLI;

import br.cefetmg.address.models.PersonModel;
import br.cefetmg.address.persist.PersistException;
import br.cefetmg.address.persist.PersonDBModel;
import java.time.LocalDate;

public class AdicionarPessoaApp implements CLIApp{

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
            PersonDBModel pdbm = new PersonDBModel();
            pdbm.insert(person);
        } catch(PersistException ex){
            System.out.println(ex.getStackTrace());
        }
    }
    
}
