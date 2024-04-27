package getUserList;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.*;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class getUser
{
RestAssured restAssured;
Response response;
 @BeforeClass
 public void setup ()
 {
  System.out.println("I am Before Class!!");
 }
 @Test
 public void do_regres_get_userlist_validation()
 {
  System.out.println("This is Get User List Test!!");
  response = get("https://reqres.in/api/users/?page=2");
  //Validate the status code
  int status_code = response.statusCode();
  Assert.assertEquals(String.valueOf(status_code),"200");
  System.out.println(response.getBody().asString());
 }
 @Test
 public void do_regres_post_call_validation()
 {
  System.out.println("This is Post Call Validation Test!!");
  String request_body_reqres = "{\n" +
          "    \"name\": \"Rachin\",\n" +
          "    \"job\": \"QA_Analyst\"\n" +
          "}";
  response = given().contentType(ContentType.JSON).body(request_body_reqres).when().post("https://reqres.in/api/users");
  System.out.println("Response body: " +response.getBody().asString());
 }
 @AfterClass
 public void close_connection()
 {
  System.out.println("I am for closing the connections!!");
 }
}
