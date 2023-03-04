package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;


public class RegistrationTests extends TestBase{


  @Test
  public void testRegistration() throws IOException, ParseException, MessagingException, ServiceException {
    skipIfNotFixed(1);
    long now = System.currentTimeMillis();
    String user = String.format("user%s", +now);
    String password = "password";
    String email = String.format("user%s@localhost", now);
    app.james().createUser(user, password);
    app.registration().start(user, email);

    List<MailMessage> mailMessages = app.james().waitForMail(user,password, 60000 );
    String confirmationLink = findConfirmationLink(mailMessages, email);
   app.registration().finish(confirmationLink, "password");
    Assert.assertTrue(app.newSession().login(user, password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }


}
