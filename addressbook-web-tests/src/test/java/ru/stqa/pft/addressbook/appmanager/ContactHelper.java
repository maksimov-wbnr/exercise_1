package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

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
      new Select (wd.findElement(By.name("new_group"))).selectByIndex(1);
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void gotoContact() {
    click(By.linkText("add new"));
  }

  public void selectContact () {
      click(By.name("selected[]"));
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
    public void initContactModification () {
      click(By.xpath("//img[@alt='Edit']"));
    }
    public void submitContactModification () {
      click(By.xpath("//input[22]"));
    }

  public void createContact(ContactData contact) {
    gotoContact();
    fillContacntForm(contact, true);
    submitContactForm();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
}

