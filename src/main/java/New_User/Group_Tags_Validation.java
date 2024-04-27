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

public class Group_Tags_Validation
{
    RegresJsonBody RJB2;
    Response response;

    @BeforeClass
    public void setup()
    {
        RJB2 = new RegresJsonBody();
        System.out.println("Before Class");
    }
    @Parameters({"userCreationURL","SSN_No","userName"})
    @Test(groups = {"smoke","regression"},priority = 0)
    public void create_new_user(String userCreationURL,String SSN_No,String userName)
    {
        setup();
        Response response = given()
                .contentType(ContentType.JSON)
                .body(RJB2.CreateUserJsonBody(userName,SSN_No))
                .when()
                .post(userCreationURL);
        int status_code = response.getStatusCode();
        String response_body = response.getBody().asString();
        String ID = response.getBody().jsonPath().getString("id");
        System.out.println(status_code);
        System.out.println(response_body);
        System.out.println("The user ID is ==>"+ID);
    }
    @Parameters({"userCreationURL"})
    @Test(groups = {"smoke"},priority = 5)
    public void test_another_one(String url)
    {
        System.out.println(url);
    }
    @Test(groups = {"regression"},priority = 1)
    public void test_another_one_100()
    {
        System.out.println("This is Test 100");
    }
    @Test(groups = {"e2e"},priority = 2)
    public void test_another_one_101()
    {
        System.out.println("This is Test 101");
    }
}
