package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public final class ContactData {
  private final String firstName;
  private final String middleName;
  private final String mobileTelephone;
  private final String email;
  private final String lastName;
  private final String group;
  private final String address;



  public ContactData(String firstName,
                     String middleName,
                     String lastName,
                     String mobileTelephone,
                     String email,
                     String group,
                     String address) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.mobileTelephone = mobileTelephone;
    this.email = email;
    this.lastName = lastName;
    this.group = group;
    this.address = address;
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
            "firstName='" + firstName + '\'' +
            ", middleName='" + middleName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }
}