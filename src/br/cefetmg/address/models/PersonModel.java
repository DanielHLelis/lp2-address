package br.cefetmg.address.models;

import java.time.LocalDate;

public class PersonModel extends Model {

  private Long id;
  private String firstName;
  private String lastName;
  private String street;
  private Integer postalCode;
  private String city;
  private LocalDate birthday;

  public PersonModel() {
    this("", "");
  }

  public PersonModel(String firstName, String lastName) {
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
    return String.format("[Person %s]: \"%s %s\"", id, firstName, lastName);
  }

  @Override
  public int hashCode() {
    return Long.hashCode(this.id);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof PersonModel) {
      return ((PersonModel) obj).getId().equals(this.getId());
    }

    return false;
  }
}