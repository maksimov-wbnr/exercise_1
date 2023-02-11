package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0 ) {
      app.contact().create(new ContactData()
              .withFirstName("First")
              .withMiddleName("Middle")
              .withLastName("Last")
              .withAddress("Address"), true);
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactDeletion() {

    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    Contacts after = app.db().contacts();
    assertThat(after.size(),  equalTo(before.size() - 1));
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListInUI();
  }
}



