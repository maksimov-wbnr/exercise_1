package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

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
      app.contact().addNew();
      app.contact().fillContactForm(new ContactData()
              .withFirstName("First")
              .withMiddleName("Middle")
              .withLastName("Last")
              .withHomePhone("919191")
              .withMobilePhone("89111111111")
              .withWorkPhone("900")
              .withEmail("tag@tag.ru")
              .withEmail2("2@tag.ru")
              .withEmail3("3@tag.ru")
              //.withGroup("test1")
              .withAddress("Address st. 52")
              .withPhone2("909090"), true);
      app.goTo().homePage();
    }
  }

  @Test (enabled = false)
  public void testContactPhones() {
   app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().InfoFromEditForm(contact);

    assertThat(contact.allPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.address(), equalTo(contactInfoFromEditForm.address()));
    assertThat(contact.allEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));


  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.email(), contact.email2(), contact.email3())
            .stream().filter((s) -> s != null && ! s.equals(""))
            .collect(Collectors.joining("\n"));

  }

  private String mergePhones(ContactData contact) {
     return Arrays.asList(contact.homePhone(), contact.mobilePhone(), contact.workPhone(), contact.phone2())
            .stream().filter((s) -> !s.equals(""))
             .map(ContactPhoneTests::cleaned)
             .collect(Collectors.joining("\n"));
  }

  public static String  cleaned (String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
