package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class HelperIssue {

  @BeforeSuite
  public void init(){
    RestAssured.authentication = RestAssured.basic("b31e382ca8445202e66b03aaf31508a3", "");
  }

  public int createIssue(Issue newIssue) throws IOException {

    String json = RestAssured.given().parameter("subject", newIssue.getSubject())
            .parameter("description", newIssue.getDescription())
            .post("https://bugify.stqa.ru/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  public Set<Issue> getIssues() throws IOException {
    String json = RestAssured.get("https://bugify.stqa.ru/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues,new TypeToken<Set<Issue>>(){}.getType());
  }



  public  boolean isIssueOpen(int issueId) {
    String json = RestAssured.get("https://bugify.stqa.ru/api/issues/"+issueId+".json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonObject jsObPars = parsed.getAsJsonObject();
    String state = jsObPars.get("issues").getAsJsonArray().get(0).getAsJsonObject().get("state_name").getAsString();
    boolean result = state.equals("Closed")?false:true;
    return result;
  }

  public void skipIfNotFixed(int issueId)  {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }






}
