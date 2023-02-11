package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.util.List;


public class ContactHelper extends HelperBase {

  private By locator;

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.firstName());
    type(By.name("middlename"), contactData.middleName());
    type(By.name("lastname"), contactData.lastName());
    type(By.name("address"), contactData.address());
    type(By.name("home"), contactData.homePhone());
    type(By.name("mobile"), contactData.mobilePhone());
    type(By.name("work"), contactData.workPhone());
    type(By.name("email"), contactData.email());
    type(By.name("email2"), contactData.email2());
    type(By.name("email3"), contactData.email3());
    type(By.name("phone2"), contactData.phone2());
    attach(By.name("photo"), contactData.photo());  //если не закоментить то будет ошибка NullPointerException
   // if (creation) {
   //   new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.group());
   // } else {
   //   Assert.assertFalse(isElementPresent(By.name("new_group")));
   // }
  }

  public void addNew() {
    click(By.linkText("add new"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void submitContactForm() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void alertAccept() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "'")).click();
  }

  public void submitContactModification() {
    click(By.xpath("//input[22]"));
  }

  public void create(ContactData contact, boolean creation) {
    addNew();
    type(By.name("firstname"), contact.firstName());
    type(By.name("lastname"), contact.lastName());
    type(By.name("address"), contact.address());

    if (creation) {
      if (contact.getGroups().size() > 0) {
        Assert.assertTrue(contact.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contact.getGroups().iterator().next().name());
      } else {
        Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
      submitContactForm();
    }
  }

  public void modify(ContactData mContact) {
    initContactModificationById(mContact.getId());
    fillContactForm(mContact, false);
    submitContactModification();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public boolean isThereGroupName(String GroupName) {
    return isElementPresent(By.xpath("//span[@class='group'][text()='" + GroupName + "']"));
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> line = wd.findElements(By.name("entry"));
    for (WebElement element : line) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withAddress(address).withAllEmails(allEmails)
              .withAllPhones(allPhones));
    }
    return contacts;
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    alertAccept();
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }


  public ContactData InfoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId())
            .withFirstName(firstname).withLastName(lastname).withAddress(address).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3).withPhone2(phone2);
  }
}

