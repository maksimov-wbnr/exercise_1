package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;

import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if (!app.getContactHelper().isThereAContact()) {
      app.goTo().groupPage();
      if (!app.getContactHelper().isThereGroupName("test1")) {
        app.group().create(new GroupData().withName("test1"));
      }
      app.getContactHelper().createContact(new ContactData(
              "MyFirst",
              "MyMiddle",
              "MyLast",
              "MyMobileTelephone",
              "MyEmail",
              "test1",
              "MyAddress"));
      app.goTo().gotoHomePage();
    }
  }

  @Test
  public void testContactModification() {

    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    ContactData mContact = new ContactData(
            before.get(index).getId(),
            "mFirst",
            "mMiddle",
            "mLast",
            "mMobile",
            "mEmail",
            null,
            "mAdr");
    app.getContactHelper().modify(index, mContact);
    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(mContact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
