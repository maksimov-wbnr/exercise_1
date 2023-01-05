package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    String GroupName = "test2";
    if (! app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().gotoGroupPage();
      if (! app.getContactHelper().isThereGroupName(GroupName)){
        app.getGroupHelper().createGroup(new GroupData(GroupName, "test2", "test3"));
      }
      app.getContactHelper().createContact(new ContactData(
              "MyFirst",
              "MyMiddle",
              "MyLast",
              "MyMobileTelephone",
              "MyEmail",
              GroupName,
              "MyAddress"));
      app.getNavigationHelper().gotoHomePage();
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContacntForm(
            new ContactData(
                    "fMod",
                    "mMod",
                    "lastMod",
                    "telMod",
                    "emailMod",
                    null,
                    "adrMod"),
                    false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
