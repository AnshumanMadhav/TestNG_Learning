package New_User;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.path.xml.config.XmlPathConfig.xmlPathConfig;

import Resuable.RegresJsonBody;

public class create_new_user
{
  RegresJsonBody RJB;
  Response response;
  public XmlPath xml_path_obj;
  @BeforeClass
  public void setup()
  {
     RJB = new RegresJsonBody();
    System.out.println("Before Class");
  }
  @Parameters({"userCreationURL","SSN_No","userName"})
  @Test(groups = {"smoke","regression"})
  public void create_a_new_user(String userCreationURL,String SSN_No,String userName)
  {
    setup();

    Response response = given()
            .contentType(ContentType.JSON)
            .body(RJB.CreateUserJsonBody(userName,SSN_No))
            .when()
            .post(userCreationURL);
    int status_code = response.getStatusCode();
    String response_body = response.getBody().asString();
    String ID = response.getBody().jsonPath().getString("id");
    System.out.println(status_code);
    System.out.println(response_body);
    System.out.println(ID);
  }
  @AfterClass
  public void TearDown()
  {
    System.out.println("This is teardown function.");
  }
  @Parameters({"celsius_to_fahrenheit_url","C_Temp","F_Temp"})
  @Test(groups = "smoke")
  public void test_class(String c_url,String c_temp,String expected_f_temp) {
    Response rs = given().contentType(ContentType.XML).header("Content-Type", "text/xml; charset=utf-8").body(RJB.Convert_Celsius_to_Fahreheit(c_temp)).when().post(c_url);
    int status_code = rs.getStatusCode();
    String response_body = rs.getBody().asString();
    xml_path_obj = new XmlPath(rs.getBody().asString()).using(xmlPathConfig().namespaceAware(false));
    String actual_f_temp = xml_path_obj.getString("soap:Envelope.soap:Body.CelsiusToFahrenheitResponse.CelsiusToFahrenheitResult");
    System.out.println(status_code);
    System.out.println(response_body);
    if (expected_f_temp.equals(actual_f_temp)) {
      System.out.println("Value matches");
    } else {
      System.out.println("Value does not matches");
    }
  }
  }
