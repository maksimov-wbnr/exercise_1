package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {



  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactForm() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContacntForm(ContactData contactData) {
    type(By.name("firstname"), contactData.firstName());
    type(By.name("middlename"),contactData.middleName());
    type(By.name("lastname"), contactData.lastName());
    type(By.name("address"), contactData.address());
    type(By.name("mobile"), contactData.mobileTelephone());
    type(By.name("email"), contactData.email());
  }

  public void selectContact() {
    click(By.xpath("//*[@id='31']"));
  }
  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }
  public void alertAccept(){
    wd.switchTo().alert().accept();
  }

}
