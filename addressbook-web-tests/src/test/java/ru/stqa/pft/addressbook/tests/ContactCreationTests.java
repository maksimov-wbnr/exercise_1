package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;


import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {
  String GroupName = "test2";

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (!app.getContactHelper().isThereGroupName(GroupName)) {
      app.group().create(new GroupData().withName(GroupName));
    }
    app.goTo().gotoHomePage();
  }

  @Test
  public void testAddNewContact() {

    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoContact();
    ContactData contact = new ContactData(
            "First",
            "Middle",
            "Last",
            "Mobile",
            "Email",
            GroupName,
            "Adr");
    app.getContactHelper().fillContacntForm(contact, true);
    app.getContactHelper().submitContactForm();
    app.goTo().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);


    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}

