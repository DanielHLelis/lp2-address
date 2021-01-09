package br.cefetmg.address.core.model.dao;

import br.cefetmg.address.core.model.domain.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages the persistence of {@link Person}
 */
public class PersonDao implements Dao<Person> {

  private Person resultSetToPerson(ResultSet rs) throws SQLException {
    Person person = new Person();
    person.setId(rs.getLong("id"));
    person.setFirstName(rs.getString("firstname"));
    person.setLastName(rs.getString("lastname"));
    person.setStreet(rs.getString("street"));
    person.setPostalCode(rs.getInt("postalcode"));
    if(rs.wasNull()){
      person.setPostalCode(null);
    }
    person.setCity(rs.getString("city"));

    Date birthday = rs.getDate("birthday");
    if(birthday != null){
      person.setBirthday(birthday.toLocalDate());
    }else {
      person.setBirthday(null);
    }

    return person;
  }

  @Override
  public void insert(Person person) throws DaoException {
    try {
      if (person == null) {
        throw new DaoException("Entidade não pode ser nula.");
      }

      Connection connection = ConnectionFactory.getConnection();

      String sql = "INSERT INTO person (firstName, lastName, street, postalcode, city, birthday) VALUES(?,?,?,?,?,?) RETURNING id";

      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setString(1, person.getFirstName());
      pstmt.setString(2, person.getLastName());
      pstmt.setString(3, person.getStreet());
      pstmt.setObject(4, person.getPostalCode(), Types.INTEGER);
      pstmt.setString(5, person.getCity());
      pstmt.setObject(6, person.getBirthday(), Types.DATE);

      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        Long id = rs.getLong("id");
        person.setId(id);
      } else {
        throw new DaoException("Não foi possível recuperar o ID da pessoa inserida");
      }

      // Em caso de exceção, podem não vir a serem encerrados
      rs.close();
      pstmt.close();
      connection.close();

    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(PersonDao.class.getName()).log(Level.SEVERE, null, ex);
      throw new DaoException(ex);
    }
  }

  @Override
  public void update(Person person) throws DaoException {
    try {
      Connection connection = ConnectionFactory.getConnection();

      String sql = "UPDATE person "
              + "   SET firstname = ?, "
              + "       lastname = ?, "
              + "       street = ?, "
              + "       postalcode = ?, "
              + "       city = ?, "
              + "       birthday = ? "
              + " WHERE id = ?;";

      PreparedStatement pstmt = connection.prepareStatement(sql);

      pstmt.setString(1, person.getFirstName());
      pstmt.setString(2, person.getLastName());
      pstmt.setString(3, person.getStreet());
      pstmt.setObject(4, person.getPostalCode(), Types.INTEGER);
      pstmt.setString(5, person.getCity());
      pstmt.setObject(6, person.getBirthday(), Types.DATE);
      pstmt.setLong(7, person.getId());
      pstmt.executeUpdate();

      pstmt.close();
      connection.close();

    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(PersonDao.class.getName()).log(Level.SEVERE, null, ex);
      throw new DaoException(ex);
    }
  }

  @Override
  public Person delete(Long id) throws DaoException {
    try {
      Person person = this.getById(id);

      Connection connection = ConnectionFactory.getConnection();

      String sql = "DELETE FROM person WHERE id = ?";

      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setLong(1, id);
      pstmt.executeUpdate();

      pstmt.close();
      connection.close();

      return person;
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(PersonDao.class.getName()).log(Level.SEVERE, null, ex);
      throw new DaoException(ex);
    }
  }

  @Override
  public Person getById(Long id) throws DaoException {
    try {
      Connection connection = ConnectionFactory.getConnection();

      String sql = "SELECT * FROM person WHERE id = ?";

      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setLong(1, id);
      ResultSet rs = pstmt.executeQuery();

      Person person = null;
      if (rs.next()) {
        person = this.resultSetToPerson(rs);
      }

      rs.close();
      pstmt.close();
      connection.close();

      return person;
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(PersonDao.class.getName()).log(Level.SEVERE, null, ex);
      throw new DaoException(ex);
    }
  }

  @Override
  public List<Person> getAll() throws DaoException {
    try {
      Connection connection = ConnectionFactory.getConnection();

      String sql = "SELECT * FROM person ORDER BY firstname, lastname;";

      PreparedStatement pstmt = connection.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();

      List<Person> listAll = new ArrayList<>();

      while (rs.next()) {
        Person person = resultSetToPerson(rs);
        listAll.add(person);
      }

      rs.close();
      pstmt.close();
      connection.close();

      return listAll;
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(PersonDao.class.getName()).log(Level.SEVERE, null, ex);
      throw new DaoException(ex);
    }
  }

}
