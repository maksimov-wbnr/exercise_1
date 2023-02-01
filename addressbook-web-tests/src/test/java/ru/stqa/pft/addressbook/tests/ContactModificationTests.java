package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if (!app.contact().isThereAContact()) {
      app.goTo().groupPage();
      if (!app.contact().isThereGroupName("test1")) {
        app.group().create(new GroupData().withName("test1"));
      }
      app.contact().create(new ContactData()
              .withFirstName("First")
              .withMiddleName("Middle")
              .withLastName("Last")
              .withHomePhone("919191")
              .withMobilePhone("89111111111")
              .withWorkPhone("900")
              .withEmail("tag@tag.ru")
              .withGroup("test1")
              .withAddress("Address st. 52"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification() {

    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();

    ContactData mContact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstName("mFirst")
            .withMiddleName("mMiddle")
            .withLastName("mLast")
            .withMobilePhone("8919111111")
            .withEmail("mtag@tag.ru")
            .withAddress("mAddress st. 52");
    app.contact().modify(mContact);
    app.goTo().homePage();
    assertThat(app.contact().count(),  equalTo(before.size()));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(mContact)));
  }


}
