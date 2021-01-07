package br.cefetmg.address.repository;

import br.cefetmg.address.models.PersonModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;
import java.time.LocalDate;
import java.util.List;

public class PersonRepositoryTest {

  void attributeCheck(PersonModel person, PersonModel person2) {
    Assertions.assertEquals(person.getFirstName(), person2.getFirstName(), "First Name integrity");
    Assertions.assertEquals(person.getLastName(), person2.getLastName(), "Last Name integrity");
    Assertions.assertEquals(person.getBirthday(), person2.getBirthday(), "Birthday integrity");
    Assertions.assertEquals(person.getCity(), person2.getCity(), "City integrity");
    Assertions.assertEquals(person.getStreet(), person2.getStreet(), "Street integrity");
    Assertions.assertEquals(person.getPostalCode(), person2.getPostalCode(), "Postal integrity");
  }

  @Test
  void persistenceBehaviorTest() throws RepositoryException{
    // Person creation
    PersonModel person = new PersonModel("Nome Nome2", "Sobrenome Sobrenome2");
    person.setBirthday(LocalDate.of(2003, 2, 7));
    person.setCity("Beagá");
    person.setPostalCode(30000001);
    person.setStreet("Av. Bandeirantes");

    // Repository creation
    PersonRepository personRepository = new PersonRepository();

    // Insertion
    personRepository.insert(person);

    Assertions.assertNotNull(person.getId(), "ID retrieving");

    // Retrieval
    PersonModel person2 = personRepository.getById(person.getId());

    Assertions.assertNotNull(person2, "Person retrieval");
    Assertions.assertEquals(person, person2, "Object defined equality");

    // Data integrity
    attributeCheck(person, person2);

    // Updating
    person2.setStreet("Al. Campinas");
    personRepository.update(person2);

    PersonModel person3 = personRepository.getById(person.getId());

    attributeCheck(person2, person3);

    // Removal
    personRepository.delete(person.getId());
    PersonModel person4 = personRepository.getById(person2.getId());

    Assertions.assertNull(person4);

    // ListAll
    personRepository.insert(person); // Should ignore and overwrite id field
    personRepository.insert(person3); // Should ignore and overwrite id field

    List<PersonModel> persons = personRepository.listAll();

    Assertions.assertTrue(persons.contains(person), "Contains person");
    Assertions.assertFalse(persons.contains(person2), "Doesn't contain person2");
    Assertions.assertTrue(persons.contains(person3), "Contains person3");

    personRepository.delete(person.getId());
    personRepository.delete(person3.getId());
  }
}
