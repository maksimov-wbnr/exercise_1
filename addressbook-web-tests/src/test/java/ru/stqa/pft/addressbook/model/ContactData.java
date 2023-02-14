package ru.stqa.pft.addressbook.model;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")

public  class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private  int id = Integer.MAX_VALUE;  //в лекции 7.3 тут без значения Integer.MAX_VALUE

  @Column(name = "firstName")
  private  String firstName;

  @Column(name = "lastName")
  private  String lastName;

  @Column(name = "middleName")
  private  String middleName;

  @Column(name = "home")
  @Type(type = "text")
  private  String homePhone;

  @Column(name = "mobile")
  @Type(type = "text")
  private  String mobilePhone;

  @Column(name = "work")
  @Type(type = "text")
  private  String workPhone;

  @Transient
  private  String allPhones;

  @Column(name = "email")
  @Type(type = "text")
  private  String email;

  @Column(name = "email2")
  @Type(type = "text")
  private  String email2;

  @Column(name = "email3")
  @Type(type = "text")
  private  String email3;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable (name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  @Column(name = "address")
  @Type(type = "text")
  private  String address;

  @Column(name = "phone2")
  @Type(type = "text")
  private  String phone2;

  @Transient
  private  String allEmails;

  @Column(name = "photo")
  @Type(type = "text")
  private  String   photo;




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

  public Groups getGroups() {
    return new Groups(groups);
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
    this.photo = photo.getPath();
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
    if (photo != null) {
      return new File(photo);
    } else {
      return null;
    }
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
  public ContactData delGroup(GroupData group) {
    groups.remove(group);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (!Objects.equals(firstName, that.firstName)) return false;
    if (!Objects.equals(lastName, that.lastName)) return false;
    return Objects.equals(address, that.address);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", middleName='" + middleName + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", email='" + email + '\'' +
            ", address='" + address + '\'' +
            '}';
  }



}