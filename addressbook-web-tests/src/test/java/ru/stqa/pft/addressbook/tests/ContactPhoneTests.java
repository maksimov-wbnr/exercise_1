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

    assertThat(contact.allPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.address(), equalTo(contactInfoFromEditForm.address()));
    assertThat(contact.email(), equalTo(contactInfoFromEditForm.email()));


  }

  private String mergePhones(ContactData contact) {
     return Arrays.asList(contact.homePhone(), contact.mobilePhone(), contact.workPhone())
            .stream().filter((s) -> !s.equals(""))
             .map(ContactPhoneTests::cleaned)
             .collect(Collectors.joining("\n"));
  }

  public static String  cleaned (String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
