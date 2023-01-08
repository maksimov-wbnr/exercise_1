package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  private By locator;

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContacntForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.firstName());
    type(By.name("middlename"), contactData.middleName());
    type(By.name("lastname"), contactData.lastName());
    type(By.name("address"), contactData.address());
    type(By.name("mobile"), contactData.mobileTelephone());
    type(By.name("email"), contactData.email());
    if (creation){
      new Select (wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

    public void gotoContact() {
    click(By.linkText("add new"));
  }

    public void selectContact (int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    }
    public void submitContactForm () {
      click(By.xpath("//div[@id='content']/form/input[21]"));
    }
    public void deleteSelectedContacts () {
      click(By.xpath("//input[@value='Delete']"));
    }
    public void alertAccept () {
      wd.switchTo().alert().accept();
    }
    public void initContactModification (int index) {
      wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }
    public void submitContactModification () {
      click(By.xpath("//input[22]"));
    }

    public void createContact(ContactData contact) {
     gotoContact();
     fillContacntForm(contact, true);
     submitContactForm();
  }
  public  void modify(int index, ContactData mContact) {
    initContactModification(index);
    fillContacntForm(mContact,false);
    submitContactModification();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public boolean isThereGroupName(String GroupName){
    return isElementPresent(By.xpath("//span[@class='group'][text()='"+GroupName+"']"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

 public List<ContactData> getContactList() {
   List<ContactData> contacts = new ArrayList<ContactData>();
   List<WebElement> line = wd.findElements(By.name("entry"));
   for (WebElement element : line) {
     int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
     List<WebElement> cells = element.findElements(By.tagName("td"));
     String lastName = cells.get(1).getText();
     String firstName = cells.get(2).getText();
     ContactData contact = new ContactData(id,firstName,null,lastName,null,null,null,null);
     contacts.add(contact);
   }
   return contacts;
 }

}

