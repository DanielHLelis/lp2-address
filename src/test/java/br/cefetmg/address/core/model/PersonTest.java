package br.cefetmg.address.core.model;

import br.cefetmg.address.core.model.dao.ConnectionFactory;
import br.cefetmg.address.core.model.dao.DaoException;
import br.cefetmg.address.core.model.dao.PersonDao;
import br.cefetmg.address.core.model.domain.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class PersonTest {


  void personIdentityCheck(Person person, Person person2) {
    Assertions.assertEquals(person.getId(), person2.getId(), "ID integrity");
    Assertions.assertEquals(person.getFirstName(), person2.getFirstName(), "First Name integrity");
    Assertions.assertEquals(person.getLastName(), person2.getLastName(), "Last Name integrity");
    Assertions.assertEquals(person.getBirthday(), person2.getBirthday(), "Birthday integrity");
    Assertions.assertEquals(person.getCity(), person2.getCity(), "City integrity");
    Assertions.assertEquals(person.getStreet(), person2.getStreet(), "Street integrity");
    Assertions.assertEquals(person.getPostalCode(), person2.getPostalCode(), "Postal integrity");
  }

  @Test
  @Order(1)
  void connectionTest(){
    Assertions.assertDoesNotThrow(ConnectionFactory::getConnection, "ConnectionFactory exception check");
  }

  @Test
  @Order(2)
  void persistenceBehaviorTest() throws DaoException {
    // Person creation
    Person person = new Person("Nome Nome2", "Sobrenome Sobrenome2");
    person.setBirthday(LocalDate.of(2003, 2, 7));
    person.setCity("Beagá");
    person.setPostalCode(30000001);
    person.setStreet("Av. Bandeirantes");

    // Dao creation
    PersonDao personRepository = new PersonDao();

    // Insertion
    personRepository.insert(person);

    Assertions.assertNotNull(person.getId(), "ID retrieving");

    // Retrieval
    Person person2 = personRepository.getById(person.getId());

    Assertions.assertNotNull(person2, "Person retrieval");
    Assertions.assertEquals(person, person2, "Object defined equality");

    // Data integrity
    personIdentityCheck(person, person2);

    // Updating
    person2.setStreet("Al. Campinas");
    personRepository.update(person2);

    Person person3 = personRepository.getById(person.getId());

    personIdentityCheck(person2, person3);

    // Removal
    personRepository.delete(person.getId());
    Person person4 = personRepository.getById(person2.getId());

    Assertions.assertNull(person4);

    // ListAll
    personRepository.insert(person); // Should ignore and overwrite id field
    personRepository.insert(person3); // Should ignore and overwrite id field

    List<Person> persons = personRepository.getAll();

    Assertions.assertTrue(persons.contains(person), "Contains person");
    Assertions.assertFalse(persons.contains(person2), "Doesn't contain person2");
    Assertions.assertTrue(persons.contains(person3), "Contains person3");

    personRepository.delete(person.getId());
    personRepository.delete(person3.getId());
  }

  @Test
  @Order(3)
  void emptyPerson() throws DaoException{
    PersonDao personDao = new PersonDao();
    Person person = new Person();

    personDao.insert(person);
    Assertions.assertNotNull(person.getId());

    personDao.delete(person.getId());
  }
}
