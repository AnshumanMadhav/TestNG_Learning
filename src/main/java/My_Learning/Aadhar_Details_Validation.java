package My_Learning;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import My_Learning.My_Learning;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static My_Learning.Post_Req_Body.aadhar_details;
import static My_Learning.insert_records_from_xl.insert_records;
import static My_Learning.Read_Property_File_Data.read_properties;
import static io.restassured.RestAssured.given;

public class Aadhar_Details_Validation
{
    Connection connection;
    Response post_aadhar_response;

    public static void main(String[] args) throws SQLException, IOException
    {
      insert_records();
      Aadhar_Details_Validation ad = new Aadhar_Details_Validation();
    }
    public void aadhar_finder()
    {
        try
        {
         String test_aadhar = read_properties("aadhar_no");
         System.out.println(test_aadhar);
         connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306","root","tiger");
         Statement stmt =connection.createStatement();
         ResultSet result =stmt.executeQuery("select distinct Aadhar_No from maven_practise.aadhar_details;");
         ArrayList<String> aadhar_nos = new ArrayList<>();
         while(result.next())
          {
           aadhar_nos.add(result.getString("Aadhar_No"));
          }
          System.out.println(aadhar_nos);
          if (aadhar_nos.contains(test_aadhar))
          {
            System.out.println("Aadhar Exists");
            get_aadhar_details(test_aadhar);
          }
          else
          {
            System.out.println("Aadhar does not Exists");
          }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void get_aadhar_details(String aadhar_no)
    {
        try
        {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306","root","tiger");
            String post_url = "https://reqres.in/api/users";
            Statement stmt =connection.createStatement();
            ResultSet result =stmt.executeQuery("select * from maven_practise.aadhar_details where Aadhar_No = "+aadhar_no+";");
            while(result.next())
            {
                String firstname =result.getString("Fname");
                String lastname =result.getString("Lname");
                long aadhar =result.getLong("Aadhar_No");
                String address = result.getString("Address");
                long phone = result.getLong("Phone");
                System.out.println(firstname + " " +lastname + " " +aadhar + " " +address + " " +phone);
                String post_request_body = aadhar_details(firstname,lastname,aadhar,address,phone);
                post_aadhar_response = given().contentType(ContentType.JSON).body(post_request_body).when().post(post_url);
                System.out.println(post_aadhar_response.getBody().asString());
                String first_name_api = post_aadhar_response.getBody().jsonPath().getString("Fname");
                String last_name_api = post_aadhar_response.getBody().jsonPath().getString("Lname");
                long aadhar_api = post_aadhar_response.getBody().jsonPath().getLong("Aadhar_No");
                String address_api = post_aadhar_response.getBody().jsonPath().getString("Address");
                long phone_api = post_aadhar_response.getBody().jsonPath().getLong("Phone");
                String id_api = post_aadhar_response.getBody().jsonPath().getString("id");
                String created_at_api = post_aadhar_response.getBody().jsonPath().getString("createdAt");
                System.out.println(first_name_api + " " +last_name_api + " " +aadhar_api + " " +address_api + " " +phone_api + " " +id_api + " " +created_at_api);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
