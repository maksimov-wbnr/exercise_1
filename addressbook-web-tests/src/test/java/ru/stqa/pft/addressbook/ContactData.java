package ru.stqa.pft.addressbook;

import java.util.Objects;

public final class ContactData {
  private final String firstName;
  private final String middleName;
  private final String mobileTelephone;
  private final String email;
  private final String lastName;
  private final String address;

  public ContactData(String firstName, String middleName, String mobileTelephone, String email, String lastName,
                     String address) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.mobileTelephone = mobileTelephone;
    this.email = email;
    this.lastName = lastName;
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

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (ContactData) obj;
    return Objects.equals(this.firstName, that.firstName) &&
            Objects.equals(this.middleName, that.middleName) &&
            Objects.equals(this.mobileTelephone, that.mobileTelephone) &&
            Objects.equals(this.email, that.email) &&
            Objects.equals(this.lastName, that.lastName) &&
            Objects.equals(this.address, that.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, middleName, mobileTelephone, email, lastName, address);
  }

  @Override
  public String toString() {
    return "ContactData[" +
            "firstName=" + firstName + ", " +
            "middleName=" + middleName + ", " +
            "mobileTelephone=" + mobileTelephone + ", " +
            "email=" + email + ", " +
            "lastName=" + lastName + ", " +
            "address=" + address + ']';
  }

}