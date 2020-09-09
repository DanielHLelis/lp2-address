package br.cefetmg.address.CLI;

import br.cefetmg.address.models.PersonModel;
import br.cefetmg.address.persist.PersistException;
import br.cefetmg.address.persist.PersonDBModel;
import java.time.LocalDate;

public class AtualizarPessoaApp implements CLIApp {

    private String stringOr(Object a, Object b) {     
        if (a == null || a.toString().replaceAll("\\s", "").equals("")) {
            if (b == null) {
                return null;
            }
            return b.toString();
        }

        return a.toString();
    }

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

            person.setFirstName(
                    stringOr(main.query("Primeiro nome [" + person.getFirstName() + "]:"), person.getFirstName()));
            person.setLastName(
                    stringOr(main.query("Último nome [" + person.getLastName() + "]:"), person.getLastName()));
            person.setStreet(stringOr(main.query("Rua [" + person.getStreet() + "]:"), person.getStreet()));
            person.setPostalCode(Integer
                    .parseInt(stringOr(main.query("CEP [" + person.getPostalCode() + "]:"), person.getPostalCode())));
            person.setCity(stringOr(main.query("Cidade [" + person.getCity() + "]:"), person.getCity()));
            person.setBirthday(LocalDate.parse(stringOr(
                    main.query("Nascimento (AAAA-MM-DD) [" + person.getBirthday() + "]:"), person.getBirthday())));

        } catch (PersistException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

}
