package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public  class ContactData {

  private  int id = Integer.MAX_VALUE;
  private  String firstName;
  private  String middleName;
  private  String mobileTelephone;
  private  String email;
  private  String lastName;
  private  String group;
  private  String address;


  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public ContactData withMobileTelephone(String mobileTelephone) {
    this.mobileTelephone = mobileTelephone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public int getId() {
    return id;
  }

  public String firstName() {
    return firstName;
  }



  public String middleName() {
    return middleName;
  }

  public String mobileTelephone() {
    return mobileTelephone;
  }

  public String email() {
    return email;
  }

  public String lastName() {
    return lastName;
  }

  public String address() {
    return address;
  }
  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (!Objects.equals(firstName, that.firstName)) return false;
    return Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    int result = firstName != null ? firstName.hashCode() : 0;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }

}