package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class AddNewContact extends TestBase {


  @Test
  public void testAddNewContact() {
  app.getNavigationHelper().gotoContact();
  app.getContactHelper().fillContacntForm(new ContactData("MyFirst", "MyMiddle", "MyLast", "MyAddress", "MyMobileTelephone", "MyEmail"));
  app.getContactHelper().submitContactForm();
  }

}
