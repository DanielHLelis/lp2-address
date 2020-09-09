package br.cefetmg.address;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressApp {
 
    private final static String dbDriver = "org.postgresql.Driver";
    private final static String dbURL = "jdbc:postgresql://localhost:5432/address";
    private final static String user = "postgres";
    private final static String pass = "postgres";

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(dbDriver);
        return DriverManager.getConnection(dbURL, user, pass);
    }
       
    public void insert(Person person) throws Exception {

        try {
            if (person == null) {
                throw new Exception("Entidade não pode ser nula.");
            }

            Connection connection = this.getConnection();

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
            Logger.getLogger(AddressApp.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    public void update(Person person) throws Exception {
        
        try {
           Connection connection = getConnection();

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
            Logger.getLogger(AddressApp.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    public Person delete(Long personId) throws Exception {

        try {
            Person person = this.getPersonById(personId);

            Connection connection = this.getConnection();

            String sql = "DELETE FROM person WHERE id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, personId);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return person;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddressApp.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    public Person getPersonById(Long personId) throws Exception {

        try {
            Connection connection = this.getConnection();

            String sql = "SELECT * FROM person WHERE id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, personId);
            ResultSet rs = pstmt.executeQuery();

            Person person = null;
            if (rs.next()) {
                person = new Person();
                person.setId(rs.getLong("id"));
                person.setFirstName(rs.getString("firstname"));
                person.setLastName(rs.getString("lastname"));
                person.setStreet(rs.getString("street"));
                person.setPostalCode(rs.getInt("postalcode"));
                person.setCity(rs.getString("city"));
                person.setBirthday(rs.getDate("birthday").toLocalDate());
            }

            rs.close();
            pstmt.close();
            connection.close();

            return person;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddressApp.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    public List<Person> listAll() throws Exception {
        try {
            Connection connection = this.getConnection();

            String sql = "SELECT * FROM person ORDER BY firstname, lastname;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List<Person> listAll = new ArrayList<>();
            if (rs.next()) {
                do {
                    Person person = new Person();
                    person.setId(rs.getLong("id"));
                    person.setFirstName(rs.getString("firstname"));
                    person.setLastName(rs.getString("lastname"));
                    person.setStreet(rs.getString("street"));
                    person.setPostalCode(rs.getInt("postalcode"));
                    person.setCity(rs.getString("city"));
                    person.setBirthday(rs.getDate("birthday").toLocalDate());
                    listAll.add(person);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            connection.close();

            return listAll;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddressApp.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }    
    
    
    public static void main(String[] args) {
        
        Person p = new Person();
        p.setFirstName("Jose");
        p.setLastName("Silva");
        p.setStreet("Rua dos Jornalistas, 125");
        p.setPostalCode(123415);
        p.setCity("Belo Horizonte");
        p.setBirthday(LocalDate.of(2010, Month.JANUARY, 27));
       
        try {
            AddressApp app = new AddressApp();
            app.insert(p);
       
            List<Person> pList = app.listAll();
            
            for (Person q: pList)
                System.out.println(p.getFirstName() + " " + p.getLastName());
        
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}