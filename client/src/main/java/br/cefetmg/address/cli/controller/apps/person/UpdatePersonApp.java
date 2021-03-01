package br.cefetmg.address.cli.controller.apps.person;

import br.cefetmg.address.cli.controller.apps.CLIApp;
import br.cefetmg.address.cli.view.ErrorView;
import br.cefetmg.address.cli.view.UpdatePersonView;
import br.cefetmg.address.cli.view.View;
import br.cefetmg.address.controller.ControllerException;
import br.cefetmg.address.controller.PersonController;
import br.cefetmg.address.model.domain.Person;
import br.cefetmg.address.utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class UpdatePersonApp extends CLIApp {

  @Override
  public String getDescription() {
    return "atualizar uma pessoa";
  }

  @Override
  public void run() throws ControllerException {
    String id = query("ID").trim();

    Long idLong = Long.parseLong(id);

    PersonController personController = new PersonController();
    Person person = personController.getPersonById(idLong);

    if (person == null) {
      View view = new ErrorView("Pessoa inexistente");
      out.println(view);
      return;
    }

    String firstName = Utils.stringOr(
            person.getFirstName(),
            query("Primeiro nome (" + person.getFirstName() + ")").trim()
    );
    String lastName = Utils.stringOr(
            person.getLastName(),
            query("Último nome (" + person.getLastName() + ")").trim()
    );
    String street = Utils.stringOr(
            person.getStreet(),
            query("Rua (" + person.getStreet() + ")").trim()
    );
    String postalCode = Utils.stringOr(
            person.getPostalCode(),
            query("Código postal (" + person.getPostalCode() + ")").trim()
    );
    String city = Utils.stringOr(
            person.getCity(),
            query("Cidade (" + person.getCity() + ")").trim()
    );
    String birthday = Utils.stringOr(
            person.getBirthday(),
            query("Nascimento (" + Utils.stringOr(person.getBirthday(), "yyyy-mm-dd") + ")").trim()
    );

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


    person.setFirstName(firstName);
    person.setLastName(lastName);
    person.setStreet(street);
    person.setPostalCode(postalCodeInt);
    person.setCity(city);
    person.setBirthday(birthdayDate);

    personController.updatePerson(person);

    View view = new UpdatePersonView(person);
    out.println(view.render());
  }
}