package Hamcrest_Scenarios;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import Resuable.common;
import Resuable.RegresJsonBody;

public class rest_hamcrest
{
    public common cmn;
    public RegresJsonBody rj;
@BeforeClass
public void setup()
{
    cmn = new common();
    rj = new RegresJsonBody();
}
@Test
public void validate_regres_get_api_response()
{
    given()
            .relaxedHTTPSValidation().when()
            .get("https://reqres.in/api/users?page=2")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
            .body("page",equalTo(2))
            .body("per_page",equalTo(6))
            .body("total",equalTo(12))
            .body("total_pages",equalTo(2))
            .body("data[0].id",equalTo(7))
            .body("data[0].email",equalTo(cmn.read_properties("email")))
            .body("data[0].first_name",equalTo(cmn.read_properties("first_name")))
            .body("data[0].last_name",equalTo(cmn.read_properties("last_name")))
            .body("data[0].avatar",equalTo("https://reqres.in/img/faces/7-image.jpg"))
            .body("support.url",equalTo("https://reqres.in/#support-heading"))
            .body("support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

}
    @Test
    public void resres_test_First_POST_Call()
    {
        String name = "Sachin";
        String job = "QA_Lead";
        String body = rj.User_Job(name,job);
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(rj.User_Job(name,job))
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("name" , equalTo("Sachin"))
                .body("job" , equalTo("QA_Lead"))
                .body("$",hasKey("id"))
                .body("id",is(notNullValue()))
                .body("$",hasKey("createdAt"))
                .body("createdAt",is(notNullValue()));
    }
}
