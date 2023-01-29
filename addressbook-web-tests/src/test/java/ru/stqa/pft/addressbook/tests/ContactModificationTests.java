package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

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
              .withMobileTelephone("Mobile")
              .withEmail("Email")
              .withGroup("test1")
              .withAddress("Adr"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification() {

    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();

    ContactData mContact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstName("mFirst")
            .withMiddleName("mMiddle")
            .withLastName("mLast")
            .withMobileTelephone("mMobile")
            .withEmail("mEmail")
            .withAddress("mAdr");
    app.contact().modify(mContact);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(mContact);
    Assert.assertEquals(before, after);
  }


}
