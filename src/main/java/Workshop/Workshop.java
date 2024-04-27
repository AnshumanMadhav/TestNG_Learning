package Workshop;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class Workshop
{
 // Use TestNG Framework
 // READ GET API from properties file
 // Create one XL Table where population vs year details are given
 // Hit the URL and read year vs population and match the data with XL Sheet
 // If data matches then update the details in an Extent Report and create a final extent report
 Response get_response;
 String get_url = "https://datausa.io/api/data?drilldowns=Nation&measures=Population";

 public static void main(String[] args)
 {
        Workshop wk = new Workshop();
        wk.get_population_details_from_api();
        System.out.println("**** RESPONSE FROM XL ****");
        //wk.get_population_details_from_xl();
 }
 public void get_population_details_from_api()
 {
     get_response = given().contentType(ContentType.JSON).when().get(get_url);
     int year_count = get_response.getBody().jsonPath().getList("data.Year").size();
     System.out.println("**** RESPONSE FROM API ****");
     for(int a = 0 ; a < year_count ; a++)
     {
       int year = get_response.getBody().jsonPath().getInt("data.Year["+a+"]");
       int population = get_response.getBody().jsonPath().getInt("data.Population["+a+"]");
       System.out.println("Year " +year+ " had population " +population);
     }
 }
 public void get_population_details_from_xl(String columnName)
 {
   String data = null;
   try
   {
    String XLFilePath = "C:/Users/anshumanm/Downloads/Population_Details.xlsx";
    FileInputStream myxlfile = new FileInputStream(XLFilePath);
    Workbook workbook = new XSSFWorkbook(myxlfile);
    Sheet sheet = workbook.getSheet("Sheet1");
    int last_row = sheet.getLastRowNum();
    for (int i =1;i<=last_row;i++)
    {
      Row row = sheet.getRow(i);
      int last_cell = row.getLastCellNum();
      Cell cell = row.getCell(0);
      String runtimeTCName = cell.getStringCellValue();

      for (int j =0; j< last_cell; j++)
      {
       // Cell cell = row.getCell(j);
        data = sheet.getRow(i).getCell(j).toString();
        System.out.println("The XL Value is:" +data);
      }
    }
   }
   catch (Exception e)
   {
    e.printStackTrace();
   }
 }
}
