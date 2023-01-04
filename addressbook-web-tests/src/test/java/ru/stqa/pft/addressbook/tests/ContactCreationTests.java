package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testAddNewContact() {
    int before = app.getContactHelper().getContactCount();
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getContactHelper().isThereGroupName()){
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
  int after = app.getContactHelper().getContactCount();
  Assert.assertEquals(after,before + 1);

  }

}
