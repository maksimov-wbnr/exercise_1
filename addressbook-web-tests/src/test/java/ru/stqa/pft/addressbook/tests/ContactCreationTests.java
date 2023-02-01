package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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

    Contacts before = app.contact().all();
    app.contact().addNew();
    ContactData contact = new ContactData()
            .withFirstName("First")
            .withMiddleName("Middle")
            .withLastName("Last")
            .withHomePhone("919191")
            .withMobilePhone("89111111111")
            .withWorkPhone("900")
            .withEmail("tag@tag.ru")
            .withGroup(GroupName)
            .withAddress("Address st. 52");
    app.contact().fillContacntForm(contact, true);
    app.contact().submitContactForm();
    app.goTo().homePage();
    assertThat(app.contact().count(),  equalTo(before.size() + 1));
    Contacts after = app.contact().all();


    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

  }

}

