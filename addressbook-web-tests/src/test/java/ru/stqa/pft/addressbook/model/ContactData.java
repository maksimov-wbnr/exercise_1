package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public final class ContactData {
  private final String id;
  private final String firstName;
  private final String middleName;
  private final String mobileTelephone;
  private final String email;
  private final String lastName;
  private final String group;
  private final String address;



  public ContactData(String id,
                     String firstName,
                     String middleName,
                     String lastName,
                     String mobileTelephone,
                     String email,
                     String group,
                     String address) {
    this.id = id;
    this.firstName = firstName;
    this.middleName = middleName;
    this.mobileTelephone = mobileTelephone;
    this.email = email;
    this.lastName = lastName;
    this.group = group;
    this.address = address;
  }

  public ContactData(
                     String firstName,
                     String middleName,
                     String lastName,
                     String mobileTelephone,
                     String email,
                     String group,
                     String address) {
    this.id = null;
    this.firstName = firstName;
    this.middleName = middleName;
    this.mobileTelephone = mobileTelephone;
    this.email = email;
    this.lastName = lastName;
    this.group = group;
    this.address = address;
  }

  public String getId() {
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

    if (!Objects.equals(id, that.id)) return false;
    if (!Objects.equals(firstName, that.firstName)) return false;
    return Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }
}