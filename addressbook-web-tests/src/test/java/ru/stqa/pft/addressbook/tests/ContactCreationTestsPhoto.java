package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;

public class ContactCreationTestsPhoto extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    app.contact().addNew();
    File photo = new File("src/test/resources/images.jpg");
    app.contact().fillContactForm(new ContactData().withFirstName("testname").withLastName("testlast").withPhoto(photo).withGroup("test1"),true);
    app.contact().submitContactForm();
    app.goTo().homePage();
  }
  //src/test/resources/images.jpg



}