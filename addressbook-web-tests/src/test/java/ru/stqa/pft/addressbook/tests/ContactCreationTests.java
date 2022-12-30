package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testAddNewContact() {
  app.getContactHelper().gotoContact();
  app.getContactHelper().fillContacntForm(
          new ContactData(
                  "MyFirst",
                  "MyMiddle",
                  "MyLast",
                  "MyMobileTelephone",
                  "MyEmail",
                  "test1",
                  "MyAddress"),
                  true);
  app.getContactHelper().submitContactForm();
  app.getNavigationHelper().gotoHomePage();

  }

}
