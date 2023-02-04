package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  String GroupName = "test2";

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[]{new ContactData().withLastName(split[0]).withFirstName(split[1]).withAddress(split[2])
              .withEmail(split[3]).withMobilePhone(split[4]).withGroup(GroupName)});
      line = reader.readLine();
    }
    return list.iterator();
  }


  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (!app.contact().isThereGroupName(GroupName)) {
      app.group().create(new GroupData().withName(GroupName));
    }
    app.goTo().homePage();
  }

  @Test(dataProvider = "validContacts")
  public void testAddNewContact(ContactData contact) {
    Contacts before = app.contact().all();
    app.contact().addNew();
    app.contact().fillContactForm(contact, true);
    app.contact().submitContactForm();
    app.goTo().homePage();
    assertThat(app.contact().count(),  equalTo(before.size() + 1));
    Contacts after = app.contact().all();


    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

  }

}

