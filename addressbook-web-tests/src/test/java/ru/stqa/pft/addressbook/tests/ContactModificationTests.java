package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){

    if (! app.getContactHelper().isThereAContact()){
      app.getNavigationHelper().gotoGroupPage();
      if (! app.getContactHelper().isThereGroupName("test1")){
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
    app.getContactHelper().initContactModification(before.size()-1);
    ContactData mContact = new ContactData(
            before.get(before.size()-1).getId(),
            "mFirst",
            "mMiddle",
            "mLast",
            "mMobile",
            "mEmail",
            null,
            "mAdr");
    app.getContactHelper().fillContacntForm(mContact,false);

    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(mContact);
    Comparator<? super ContactData> byId = (c1,c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
