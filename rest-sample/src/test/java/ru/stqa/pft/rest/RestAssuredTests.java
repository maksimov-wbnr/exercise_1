package ru.stqa.pft.rest;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Set;

public class RestAssuredTests extends HelperIssue{

  @Test
  public void testCreationIssue() throws IOException {
    skipIfNotFixed(6);
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test2").withDescription("descrTest2");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    Assert.assertEquals(newIssues, oldIssues);
  }

}
