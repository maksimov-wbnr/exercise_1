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
    if (!app.contact().isThereGroupName(GroupName)) {
      app.group().create(new GroupData().withName(GroupName));
    }
    app.goTo().homePage();
  }

  @Test
  public void testAddNewContact() {

    List<ContactData> before = app.contact().list();
    app.contact().addNew();
    ContactData contact = new ContactData()
            .withFirstName("First")
            .withMiddleName("Middle")
            .withLastName("Last")
            .withMobileTelephone("Mobile")
            .withEmail("Email")
            .withGroup(GroupName)
            .withAddress("Adr");
    app.contact().fillContacntForm(contact, true);
    app.contact().submitContactForm();
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}

