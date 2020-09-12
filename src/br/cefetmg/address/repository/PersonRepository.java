package br.cefetmg.address.repository;

import br.cefetmg.address.models.PersonModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages the persistence of {@link PersonModel}
 */
public class PersonRepository implements Repository<PersonModel> {

  private PersonModel resultSetToPerson(ResultSet rs) throws SQLException {
    PersonModel person = new PersonModel();
    person.setId(rs.getLong("id"));
    person.setFirstName(rs.getString("firstname"));
    person.setLastName(rs.getString("lastname"));
    person.setStreet(rs.getString("street"));
    person.setPostalCode(rs.getInt("postalcode"));
    person.setCity(rs.getString("city"));
    person.setBirthday(rs.getDate("birthday").toLocalDate());

    return person;
  }

  @Override
  public void insert(PersonModel person) throws RepositoryException {
    try {
      if (person == null) {
        throw new RepositoryException("Entidade não pode ser nula.");
      }

      Connection connection = ConnectionFactory.getConnection();

      String sql = "INSERT INTO person (firstName, lastName, street, postalcode, city, birthday) VALUES(?,?,?,?,?,?) RETURNING id";

      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setString(1, person.getFirstName());
      pstmt.setString(2, person.getLastName());
      pstmt.setString(3, person.getStreet());
      pstmt.setInt(4, person.getPostalCode());
      pstmt.setString(5, person.getCity());
      pstmt.setDate(6, java.sql.Date.valueOf(person.getBirthday()));

      ResultSet rs = pstmt.executeQuery();

      if (rs.next()) {
        Long id = rs.getLong("id");
        person.setId(id);
      }

      rs.close();
      pstmt.close();
      connection.close();

    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(PersonRepository.class.getName()).log(Level.SEVERE, null, ex);
      throw new RepositoryException(ex);
    }
  }

  @Override
  public void update(PersonModel person) throws RepositoryException {
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
      pstmt.setInt(4, person.getPostalCode());
      pstmt.setString(5, person.getCity());
      pstmt.setDate(6, java.sql.Date.valueOf(person.getBirthday()));
      pstmt.setLong(7, person.getId());
      pstmt.executeUpdate();

      pstmt.close();
      connection.close();

    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(PersonRepository.class.getName()).log(Level.SEVERE, null, ex);
      throw new RepositoryException(ex);
    }
  }

  @Override
  public PersonModel delete(Long id) throws RepositoryException {
    try {
      PersonModel person = this.getById(id);

      Connection connection = ConnectionFactory.getConnection();

      String sql = "DELETE FROM person WHERE id = ?";

      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setLong(1, id);
      pstmt.executeUpdate();

      pstmt.close();
      connection.close();

      return person;
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(PersonRepository.class.getName()).log(Level.SEVERE, null, ex);
      throw new RepositoryException(ex);
    }
  }

  @Override
  public PersonModel getById(Long id) throws RepositoryException {
    try {
      Connection connection = ConnectionFactory.getConnection();

      String sql = "SELECT * FROM person WHERE id = ?";

      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setLong(1, id);
      ResultSet rs = pstmt.executeQuery();

      PersonModel person = null;
      if (rs.next()) {
        person = this.resultSetToPerson(rs);
      }

      rs.close();
      pstmt.close();
      connection.close();

      return person;
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(PersonRepository.class.getName()).log(Level.SEVERE, null, ex);
      throw new RepositoryException(ex);
    }
  }

  @Override
  public List<PersonModel> listAll() throws RepositoryException {
    try {
      Connection connection = ConnectionFactory.getConnection();

      String sql = "SELECT * FROM person ORDER BY firstname, lastname;";

      PreparedStatement pstmt = connection.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();

      List<PersonModel> listAll = new ArrayList<>();

      while (rs.next()) {
        PersonModel person = resultSetToPerson(rs);
        listAll.add(person);
      }

      rs.close();
      pstmt.close();
      connection.close();

      return listAll;
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(PersonRepository.class.getName()).log(Level.SEVERE, null, ex);
      throw new RepositoryException(ex);
    }
  }

}
