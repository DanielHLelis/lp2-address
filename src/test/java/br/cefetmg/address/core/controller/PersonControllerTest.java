package br.cefetmg.address.core.controller;

import br.cefetmg.address.core.model.dao.DaoException;
import br.cefetmg.address.core.model.dao.PersonDao;
import br.cefetmg.address.core.model.domain.Person;
import br.cefetmg.address.core.controller.PersonController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PersonControllerTest {
    
    @Test
    @Order(1)
    void additionTest() throws ControllerException {
        //Person creation
        Person person = new Person("Teste teste", "Testando testando");
        person.setBirthday(LocalDate.of(2002, 4, 5));
        person.setCity("Curitiba");
        person.setPostalCode(30540980);
        person.setStreet("Av. Amazonas");
        
        //Person addition
        PersonController personController = new PersonController();
        personController.addPerson(person);
    }
    
    @Test
    @Order(2)
    void deletionTestById() throws ControllerException {
        //Person creation
        Person person = new Person("Teste teste", "Testando testando");
        person.setBirthday(LocalDate.of(2002, 4, 5));
        person.setCity("Curitiba");
        person.setPostalCode(30540980);
        person.setStreet("Av. Amazonas");
        
        //Person addition
        PersonController personController = new PersonController();
        personController.addPerson(person);
        
        //Person deletion by id
        personController.deletePerson(person.getId());      
    }
    
    @Test
    @Order(3)
    void deletionTestByObject() throws ControllerException {
        //Person creation
        Person person = new Person("Teste teste", "Testando testando");
        person.setBirthday(LocalDate.of(2002, 4, 5));
        person.setCity("Curitiba");
        person.setPostalCode(30540980);
        person.setStreet("Av. Amazonas");
        
        //Person addition
        PersonController personController = new PersonController();
        personController.addPerson(person);
        
        //Person deletion by object
        personController.deletePerson(person);
    }
    
    @Test
    @Order(4)
    void updateTest() throws ControllerException{
        //Person creation
        Person person = new Person("Teste teste", "Testando testando");
        person.setBirthday(LocalDate.of(2002, 4, 5));
        person.setCity("Curitiba");
        person.setPostalCode(30540980);
        person.setStreet("Av. Amazonas");
        
        //Person addition
        PersonController personController = new PersonController();
        personController.addPerson(person);
        
        //Update 
        personController.updatePerson(person);
    }
}
