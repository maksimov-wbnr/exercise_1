package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.List;

public class PasswordReset extends TestBase {

  @Test
  public void testPasswordReset() throws MessagingException, IOException, ParseException {
    
    app.goTo().login(app.getProperty("web.adminLogin"),app.getProperty("web.adminPassword"));
    app.goTo().resetOption();
    String user = app.goTo().userReset();
    String email = user+"@local.host";
    String password = "password";
    String confirmPassword = "123456";
    app.james().deleteAllmessage(user,password);
    app.goTo().reset();
    List<MailMessage> mailMessages = app.james().waitForResetMail(user, password, 60000);
    String resetLink = findResetLink(mailMessages, email);
    app.goTo().resetPassword(resetLink);
    app.goTo().confirmReset("anonymous",confirmPassword);
    HttpSession session = app.newSession();
    Assert.assertTrue(session.login(user, confirmPassword));
    Assert.assertTrue(session.isLoggedInAs(user));


  }

  private String findResetLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }



}
