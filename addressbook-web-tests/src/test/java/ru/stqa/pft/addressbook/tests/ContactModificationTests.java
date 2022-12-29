package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContacntForm(
            new ContactData(
                    "fMod",
                    "mMod",
                    "astMod",
                    "telMod",
                    "emailMod",
                    "test1",
                    "adrMod"));
    app.getContactHelper().submitContactModification();
  }
}
