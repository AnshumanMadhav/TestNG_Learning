package My_Learning;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static Capstone_Project.read_data_from_property_file.read_properties;
import static io.restassured.RestAssured.given;

public class Credit_card_Details_Validation
{
    Connection connection;
    Response post_credit_response;
    String url = read_properties("url");
    String user = read_properties("user");
    String password = read_properties("password");
    public static void main(String[] args)
    {
      Credit_card_Details_Validation cc = new Credit_card_Details_Validation();
      cc.post_credit_details();
    }

    public void post_credit_details()
    {
        String post_url = "https://api.restful-api.dev/objects";
        String post_request_body = "{\n" +
                "\"name\": \"Mithun\",\n" +
                "\"data\": {\n" +
                "\"year\": 2019,\n" +
                "\"Credit Card Number\": 112233445566,\n" +
                "\"Limit\": \"5L\",\n" +
                "\"EXP Date\": \"05-05-2025\",\n" +
                "\"Card Type\": \"MASTER\"\n" +
                "}\n" +
                "}";
        post_credit_response = given().contentType(ContentType.JSON).body(post_request_body).when().post(post_url);
        System.out.println(post_credit_response.body().asString());

        String name_api = post_credit_response.getBody().jsonPath().getString("name");
        int year_api = post_credit_response.getBody().jsonPath().getInt("data.year");
        long credit_card_no_api = post_credit_response.getBody().jsonPath().getLong("data['Credit Card Number']");
        String credit_limit_api = post_credit_response.getBody().jsonPath().getString("data.Limit");
        String expiry_date_api = post_credit_response.getBody().jsonPath().getString("data['EXP Date']");
        String card_type_api = post_credit_response.getBody().jsonPath().getString("data['Card Type']");
        System.out.println("Name is: " +name_api);
        System.out.println("Year is: " +year_api);
        System.out.println("Credit Card No. is:" +credit_card_no_api);
        System.out.println("Credit Card Limit is:" +credit_limit_api);
        System.out.println("Expiry Date is:" +expiry_date_api);
        System.out.println("Credit Card Type is:" +card_type_api);
    }

}
