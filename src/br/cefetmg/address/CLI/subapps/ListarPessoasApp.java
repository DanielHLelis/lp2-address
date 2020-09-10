package br.cefetmg.address.CLI.subapps;

import br.cefetmg.address.CLI.CLIApp;
import br.cefetmg.address.CLI.MainApp;
import br.cefetmg.address.models.PersonModel;
import br.cefetmg.address.repository.RepositoryException;
import br.cefetmg.address.repository.PersonRepository;
import br.cefetmg.address.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ListarPessoasApp implements CLIApp {

    @Override
    public void run(MainApp main, String[] params) {
        try {
            PersonRepository pdbm = new PersonRepository();

            List<PersonModel> persons = pdbm.listAll();

            Map<String, List<Object>> personsDf = new TreeMap<>();

            personsDf.put("id", new ArrayList<>());
            personsDf.put("firstName", new ArrayList<>());
            personsDf.put("lastName", new ArrayList<>());
            personsDf.put("street", new ArrayList<>());
            personsDf.put("postalCode", new ArrayList<>());
            personsDf.put("city", new ArrayList<>());
            personsDf.put("birthday", new ArrayList<>());

            for(var p: persons){
                personsDf.get("id").add(p.getId());
                personsDf.get("firstName").add(p.getFirstName());
                personsDf.get("lastName").add(p.getLastName());
                personsDf.get("street").add(p.getStreet());
                personsDf.get("postalCode").add(p.getPostalCode());
                personsDf.get("city").add(p.getCity());
                personsDf.get("birthday").add(p.getBirthday());
            }

            System.out.println(Utils.tablify(personsDf));
        } catch (RepositoryException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

}
