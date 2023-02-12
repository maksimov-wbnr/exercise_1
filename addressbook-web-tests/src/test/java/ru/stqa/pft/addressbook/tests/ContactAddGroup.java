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

public class ContactAddGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    Contacts contacts = app.db().contacts();

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    if (app.db().contacts().size() == 0 || contactsGroups(contacts) == null) {
      app.contact().create(new ContactData()
              .withFirstName("First")
              .withLastName("Last"), false);
      app.goTo().homePage();
    }
  }


    @Test(enabled = true)
    public void testAddContactToGroup () {
      Contacts contacts = app.db().contacts();
      ContactData contactsAddGroup = contactsGroups(contacts);
      GroupData addGroup = addGroupContact();
      ContactData before = contactsAddGroup.inGroup(addGroup);
      app.contact().selectContactById(contactsAddGroup.getId());
      app.contact().addToGroup(addGroup.name());
      ContactData after = contactsAddGroup.inGroup(addGroup);

      assertThat(after, equalTo(before));
    }

    public ContactData contactsGroups(Contacts groupContact ){  //получаем список контактов для добавления в группу
      for (ContactData contact : groupContact) {
        Set<GroupData> contactsGroups = contact.getGroups();
        int allGroups = app.db().groups().size();
        if (allGroups > contactsGroups.size()) {
          return contact;
        }
      }
      return null;
    }

    public GroupData addGroupContact() {
     Contacts contacts = app.db().contacts();
     Groups groups   = app.db().groups();
     Groups groupsAddContact = contactsGroups(contacts).getGroups();
     groups.removeAll(groupsAddContact);  //удаляем группы которые есть у контакта
     GroupData groupAddСontact = groups.iterator().next(); //группа для добавления
     return groupAddСontact;
  }

  }

