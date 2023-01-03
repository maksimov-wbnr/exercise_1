package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testAddNewContact() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
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
