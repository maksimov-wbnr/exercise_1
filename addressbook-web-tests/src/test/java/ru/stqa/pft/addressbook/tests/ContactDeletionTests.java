package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

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
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().alertAccept();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() -1);
    Assert.assertEquals(before, after );
  }
}



