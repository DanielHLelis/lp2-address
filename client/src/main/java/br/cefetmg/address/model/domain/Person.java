package br.cefetmg.address.model.domain;

import java.time.LocalDate;

/**
 * data representation of a person
 *
 * @author Daniel H. Lelis
 * @author Ana Luisa
 */
public class Person extends Model {

  private Long id;
  private String firstName;
  private String lastName;
  private String street;
  private Integer postalCode;
  private String city;
  private LocalDate birthday;

  /**
   * creates an instance with only the names set as empty strings
   */
  public Person() {
    this("", "");
  }

  /**
   * create an instance with only the names set
   *
   * @param firstName person's first name
   * @param lastName  person's last name
   */
  public Person(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public Integer getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(Integer postalCode) {
    this.postalCode = postalCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  @Override
  public String repr() {
    return String.format("[Person %d]: \"%s %s\"", id, firstName, lastName);
  }

  @Override
  public int hashCode() {
    // Hashes the ID, since it should be unique
    return Long.hashCode(this.id);
  }

  @Override
  public boolean equals(Object obj) {
    // Checks if its a Person and then compares the id, since it should be unique
    if (obj instanceof Person) {
      return ((Person) obj).getId().equals(this.getId());
    }

    return false;
  }
}