package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

/*
  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      xstream.allowTypes(new Class[]{ContactData.class});
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

*/
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
       app.goTo().groupPage();
       app.group().create(new GroupData().withName("test1"));
      }
    }

    //(dataProvider = "validContacts")
    @Test
    public void testAddNewContact (){
      Groups groups = app.db().groups();
      Contacts before = app.db().contacts();
      ContactData newContact = new ContactData().withFirstName("FirstG").withLastName("LastG").withMiddleName("MiddleG")
              .withHomePhone("515151").withMobilePhone("89111111111").withWorkPhone("202020").withEmail("testG@test.com")
              .withAddress("AddressG").inGroup(groups.iterator().next());
      app.contact().create(newContact, true);
      app.goTo().homePage();
      Contacts after = app.db().contacts();
      assertThat(after.size(), equalTo(before.size() + 1));
      assertThat(after, equalTo
              (before.withAdded(newContact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
      verifyContactListInUI();


    }

  }


