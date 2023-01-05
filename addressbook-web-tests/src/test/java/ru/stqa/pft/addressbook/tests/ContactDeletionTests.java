package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoGroupPage();
      if (!app.getContactHelper().isThereGroupName("test1")) {
        app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
      }
    app.getContactHelper().createContact(new ContactData(
            "MyFirst",
            "MyMiddle",
            "MyLast",
            "MyMobileTelephone",
            "MyEmail",
            "test1",
            "MyAddress"));
    app.getNavigationHelper().gotoHomePage();
  }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().alertAccept();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);

  }
}



