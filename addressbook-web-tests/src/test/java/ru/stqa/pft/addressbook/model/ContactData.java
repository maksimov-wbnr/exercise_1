package ru.stqa.pft.addressbook.model;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;

@XStreamAlias("contact")

public  class ContactData {
  @XStreamOmitField
  private  int id = Integer.MAX_VALUE;
  private  String firstName;
  private  String lastName;
  private  String middleName;
  private  String homePhone;
  private  String mobilePhone;
  private  String workPhone;
  private  String allPhones;
  private  String email;
  private  String email2;
  private  String email3;
  private  String group;
  private  String address;
  private  String phone2;
  private  String allEmails;
  private  File   photo;




  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public ContactData withHomePhone (String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
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

  public ContactData withPhone2(String phone2) {
    this.phone2 = phone2;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public int getId() {
    return id;
  }

  public String firstName() {
    return firstName;
  }

  public String lastName() {
    return lastName;
  }

  public String middleName() {
    return middleName;
  }

  public String homePhone() {
    return homePhone;
  }

  public String mobilePhone() {
    return mobilePhone;
  }

  public String workPhone() {
    return workPhone;
  }

  public String allPhones() {
    return allPhones;
  }


  public String email() {
    return email;
  }

  public String email2() {
    return email2;
  }

  public String email3() {
    return email3;
  }

  public String group() {
    return group;
  }

  public String address() {
    return address;
  }

  public String phone2() {
    return phone2;
  }

  public String allEmails() {
    return allEmails;
  }

  public File photo() {
    return photo;
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

    return id == that.id;
  }

  @Override
  public int hashCode() {
    return id;
  }

}