package Rest_API_Assignment;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Pet_Details
{
    Response post_response;
    Response get_response;
    Response delete_response;
    public static void main(String[] args) throws IOException
    {
     Pet_Details pd = new Pet_Details();
     pd.get_pet_details();
    }
    public void get_pet_details() throws IOException
    {
        FileInputStream file = new FileInputStream("C:/Users/anshumanm/Downloads/Pet_Details.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sh = wb.getSheet("Sheet1");
        HashMap<Integer, String> pet_map = new HashMap<Integer, String>();
        for (int i = 1; i <= sh.getLastRowNum();i++)
        {
            int Pet_ID = (int)sh.getRow(i).getCell(0).getNumericCellValue();
            String Pet_Name = sh.getRow(i).getCell(1).getStringCellValue();
            pet_map.put(Pet_ID,Pet_Name);
        }

        for(Map.Entry<Integer,String> entry : pet_map.entrySet())
        {
          int petID = entry.getKey();
          String petName = entry.getValue();

          // POST RESPONSE
          String post_request_body = "{\n" +
                    "    \"id\":\""+petID+"\",\n" +
                    "    \"category\":{\"id\":0,\"name\":\"string\"},\n" +
                    "    \"name\":\""+petName+"\",\n" +
                    "    \"photoUrls\":[\"string\"],\n" +
                    "    \"tags\":[{\"id\":0,\"name\":\"string\"}],\n" +
                    "    \"status\":\"available\"\n" +
                    "}";
          String post_url = "https://petstore.swagger.io/v2/pet";
          post_response = given().contentType(ContentType.JSON).body(post_request_body).when().post(post_url);
          System.out.println(post_response.getBody().asString());
          int actual_response_code = post_response.statusCode();
          String actual_status_text = post_response.getBody().jsonPath().getString("status");

          // VALIDATING STATUS CODE IS 200 AND STATUS TEXT IS "available"
          Assert.assertEquals(String.valueOf(actual_response_code),"200");
          Assert.assertEquals(actual_status_text,"available");

          //GET RESPONSE
          String get_url = post_url + "/" + post_response.getBody().jsonPath().getString("id");
          get_response = given().contentType(ContentType.JSON).when().get(get_url);
          System.out.println(get_response.getBody().asString());
          int actual_pet_id = get_response.getBody().jsonPath().getInt("id");
          String actual_pet_name = get_response.getBody().jsonPath().getString("name");

          // VALIDATING PET_ID AND PET_NAME MATCHES BETWEEN EXCEL AND RESPONSE
          Assert.assertEquals(petID,actual_pet_id);
          Assert.assertEquals(petName,actual_pet_name);

          // DELETE RESPONSE
          String delete_url = post_url + "/" + get_response.getBody().jsonPath().getString("id");
          delete_response = given().contentType(ContentType.JSON).when().delete(delete_url);
          System.out.println(delete_response.getBody().asString());
          //AFTER DELETING VALIDATING IF WE ARE GETTING PET NOT FOUND MESSAGE AFTER WE HIT GET URL
          String get_url_new = post_url + "/" + delete_response.getBody().jsonPath().getString("message");
          get_response = given().contentType(ContentType.JSON).when().get(get_url_new);
          String actual_message = get_response.getBody().jsonPath().getString("message");
          System.out.println(get_response.getBody().asString());
          Assert.assertEquals(actual_message,"Pet not found");
        }
    }

}
