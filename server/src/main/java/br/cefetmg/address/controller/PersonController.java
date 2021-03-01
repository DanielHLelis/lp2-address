package br.cefetmg.address.controller;

import br.cefetmg.address.model.dao.DaoException;
import br.cefetmg.address.model.dao.PersonDao;
import br.cefetmg.address.model.domain.Person;

import java.util.List;

public class PersonController {

  public void addPerson(Person p) throws ControllerException {
    try {
      PersonDao personDao = new PersonDao();
      personDao.insert(p);
    } catch (DaoException e) {
      throw new ControllerException(e);
    }
  }

  public Person deletePerson(Long id) throws ControllerException {
    try {
      PersonDao personDao = new PersonDao();
      return personDao.delete(id);
    } catch (DaoException e) {
      throw new ControllerException(e);
    }
  }

  public Person deletePerson(Person p) throws ControllerException {
    return deletePerson(p.getId());
  }

  public void updatePerson(Person p) throws ControllerException {
    try {
      PersonDao personDao = new PersonDao();
      personDao.update(p);
    } catch (DaoException e) {
      throw new ControllerException(e);
    }
  }

  public Person getPersonById(Long id) throws ControllerException {
    try {
      PersonDao personDao = new PersonDao();
      return personDao.getById(id);
    } catch (DaoException e) {
      throw new ControllerException(e);
    }
  }

  public List<Person> getAllPeople() throws ControllerException {
    try {
      PersonDao personDao = new PersonDao();
      return personDao.getAll();
    } catch (DaoException e) {
      throw new ControllerException(e);
    }
  }
}
