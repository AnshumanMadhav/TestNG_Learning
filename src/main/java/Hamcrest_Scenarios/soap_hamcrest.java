package Hamcrest_Scenarios;

import com.aventstack.extentreports.gherkin.model.Given;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class soap_hamcrest
{
 // Validate first book name is "The Nightingale"
 // Validate first book Hardcover Book Price is 570
 // Validate first book Category is Cooking
 // Validate second book Category is Children

 // How to validate these SOAP Response using Hamcrest

 @Test
 public void validate_book_soap_api_response()
 {
   given()
           .relaxedHTTPSValidation().when()
           .get("https://chercher.tech/sample/api/books.xml")
           .then()
           .assertThat()
           .statusCode(HttpStatus.SC_OK)
           .body("bookstore.book[0].title",equalTo("The Nightingale"))
           .body("bookstore.book[0].price.hardcover",equalTo("570"))
           .body("bookstore.book[0].@category",equalTo("cooking"))
           .body("bookstore.book[1].@category",equalTo("children"))
           .body("bookstore.book[0].title.@lang",equalTo("en"));
 }

}
