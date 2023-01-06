package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testAddNewContact() {
    String GroupName = "test2";
    List<ContactData> before = app.getContactHelper().getContactList();

    app.getNavigationHelper().gotoGroupPage();
    if (! app.getContactHelper().isThereGroupName(GroupName)){
      app.getGroupHelper().createGroup(new GroupData(GroupName, "test2", "test3"));
    }

  app.getContactHelper().gotoContact();
  app.getContactHelper().fillContacntForm(
          new ContactData(
                  "MyFirst",
                  "MyMiddle",
                  "MyLast",
                  "MyMobileTelephone",
                  "MyEmail",
                  GroupName,
                  "MyAddress"),
                  true);
  app.getContactHelper().submitContactForm();
  app.getNavigationHelper().gotoHomePage();
  List<ContactData> after = app.getContactHelper().getContactList();
  Assert.assertEquals(after.size(),before.size() + 1);

  }

}
