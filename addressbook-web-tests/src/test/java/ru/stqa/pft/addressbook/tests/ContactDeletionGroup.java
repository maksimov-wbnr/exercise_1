package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactDeletionGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    Contacts contacts = app.db().contacts();
    //Groups groups = app.db().groups();
    if (contacts.size() == 0) {
      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test1"));
      }
      //Groups groups = app.db().groups();
      app.contact().create(new ContactData()
              .withFirstName("First")
              .withLastName("Last")
              .inGroup(app.db().groups().iterator().next()), true);
      app.goTo().homePage();
    }

    int conDelGr = app.db().contacts().iterator().next().getId();
    String groupName = app.db().groups().iterator().next().name();
    ContactData groupSize = new ContactData();
    if (groupSize.getGroups().size() == 0){
      app.contact().selectContactById(conDelGr);
      app.contact().addToGroup(groupName);
    }

  }

  @Test
  public void testContactDeletionGroup(){
    app.goTo().homePage();
    Contacts contacts = app.db().contacts();
    ContactData conDelGr = contacts.iterator().next();
    GroupData delGr = conDelGr.getGroups().iterator().next();
    app.contact().selectContactById(conDelGr.getId());
    app.contact().selectGroupDel(delGr.getId());
    app.contact().delGroup();

    ContactData after = conDelGr;

    assertThat(after, equalTo(conDelGr.delGroup(delGr)));

  }
}
