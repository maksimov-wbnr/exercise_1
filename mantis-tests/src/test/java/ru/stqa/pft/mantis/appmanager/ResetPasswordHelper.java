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

  public void resetOption(){
    click(By.xpath("//li[6]/a/span"));
    click(By.xpath("//div[2]/div[2]/div/ul/li[2]/a"));
    click(By.xpath("//tr[2]/td/a"));

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

  public String userReset() {
    String user = wd.findElement(By.name("username")).getAttribute("value");
    return user;
  }


  public void reset(){
    click(By.xpath("//input[@value='Сбросить пароль']"));
  }





}
