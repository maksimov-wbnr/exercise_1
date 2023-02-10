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

    if (app.db().contacts().size() == 0 ){
      app.contact().create(new ContactData()
              .withFirstName("First")
              .withLastName("Last")
              .withMiddleName("Middle")
              .withHomePhone("515151")
              .withMobilePhone("89191111111")
              .withWorkPhone("252525")
              .withEmail("test@tag.com")
              .withAddress("New address"));

      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification() {

    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData mContact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstName("mFirst")
            .withLastName("mLast")
            .withMiddleName("mMiddle")
            .withHomePhone("414141")
            .withMobilePhone("81111111111")
            .withWorkPhone("202020")
            .withEmail("mtest@tag.com")
            .withAddress("mNew address");
    app.contact().modify(mContact);
    app.goTo().homePage();
    Contacts after = app.db().contacts();
    assertThat(after.size(),  equalTo(before.size()));


    assertThat(after, equalTo(before.without(modifiedContact).withAdded(mContact)));
  }


}
