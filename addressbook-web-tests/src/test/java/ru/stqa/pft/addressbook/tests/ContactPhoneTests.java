package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactPhoneTests extends TestBase {

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
  public void testContactPhones() {
   app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().InfoFromEditForm(contact);

    assertThat(contact.homePhone(), equalTo(cleaned(contactInfoFromEditForm.homePhone())));
    assertThat(contact.mobilePhone(), equalTo(cleaned(contactInfoFromEditForm.mobilePhone())));
    assertThat(contact.workPhone(), equalTo(cleaned(contactInfoFromEditForm.workPhone())));

  }

  public String  cleaned (String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
