package ru.stqa.pft.mantis.appmanager;


import org.openqa.selenium.By;

public class ResetPasswordHelper extends HelperBase {

  public ResetPasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password){
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.xpath("//input[@value='Вход']"));
    type(By.name("password"), password);
    click(By.xpath("//input[@value='Вход']"));
  }

  public void userReset(String user){
    click(By.xpath("//a[@href='/mantisbt-2.25.4/manage_overview_page.php']"));
    click(By.xpath("//div[2]/div[2]/div/ul/li[2]/a"));
    click(By.linkText(user));
    click(By.xpath("//input[@value='Сбросить пароль']"));
  }

  public void resetPassword (String resetLink) {
    wd.get(resetLink);
  }

  public void confirmReset(String realname, String password){
    type(By.name("realname"), realname);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.xpath("//button[@type='submit']"));
  }




}
