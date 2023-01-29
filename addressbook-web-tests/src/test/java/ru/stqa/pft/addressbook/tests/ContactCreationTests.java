package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

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

    Set<ContactData> before = app.contact().all();
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
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}

