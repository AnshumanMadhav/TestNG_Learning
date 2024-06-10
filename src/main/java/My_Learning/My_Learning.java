package My_Learning;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static io.restassured.RestAssured.given;

public class My_Learning
{
    Connection connection;
    Response post_aadhar_response;
    public static void main(String[] args)
    {
       My_Learning mla = new My_Learning();
       mla.get_aadhar_details("2345678901");
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
              String aadhar =result.getString("Aadhar_No");
              String address = result.getString("Address");
              String phone = result.getString("Phone");
              System.out.println(firstname + " " +lastname + " " +aadhar + " " +address + " " +phone);
              String post_request_body = "{\n" +
                      "\"Fname\": \""+firstname+"\",\n" +
                      "\"Lname\": \""+lastname+"\",\n" +
                      "\"Aadhar_No\": \""+aadhar+"\",\n" +
                      "\"Address\": \""+address+"\",\n" +
                      "\"Phone\": \""+phone+"\"\n" +
                      "}";
              post_aadhar_response = given().contentType(ContentType.JSON).body(post_request_body).when().post(post_url);
              System.out.println(post_aadhar_response.getBody().asString());
            }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
    }
}
