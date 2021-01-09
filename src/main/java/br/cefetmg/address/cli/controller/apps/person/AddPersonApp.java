package br.cefetmg.address.cli.controller.apps.person;

import br.cefetmg.address.cli.controller.apps.CLIApp;
import br.cefetmg.address.cli.view.AddPersonView;
import br.cefetmg.address.cli.view.View;
import br.cefetmg.address.core.controller.ControllerException;
import br.cefetmg.address.core.controller.PersonController;
import br.cefetmg.address.core.model.domain.Person;
import br.cefetmg.address.utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddPersonApp extends CLIApp {

  @Override
  public String getDescription() {
    return "inserir pessoa";
  }

  @Override
  public void run() throws ControllerException {
    String firstName = query("Primeiro nome").trim();
    String lastName = query("Último nome").trim();
    String street = query("Rua").trim();
    String postalCode = query("Código postal").trim();
    String city = query("Cidade").trim();
    String birthday = query("Nascimento (yyyy-mm-dd)").trim();

    Integer postalCodeInt = null;
    if(!postalCode.isEmpty()){
      try{
        postalCodeInt = Integer.parseInt(postalCode);
      }catch (NumberFormatException e){
        throw new ControllerException("Código postal inválido", e);
      }
    }

    LocalDate birthdayDate = null;
    if(!birthday.isEmpty()){
      try{
        birthdayDate = LocalDate.parse(birthday);
      }catch (DateTimeParseException e){
        throw new ControllerException("Aniversário inválido", e);
      }
    }

    Person person = new Person();

    person.setFirstName(Utils.stringOr(firstName, null));
    person.setLastName(Utils.stringOr(lastName, null));
    person.setStreet(Utils.stringOr(street, null));
    person.setPostalCode(postalCodeInt);
    person.setCity(Utils.stringOr(city, null));
    person.setBirthday(birthdayDate);

    PersonController personController = new PersonController();
    personController.addPerson(person);

    View view = new AddPersonView(person);
    out.println(view.render());
  }
}
