package Workshop;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

 public static void main(String[] args) throws IOException {
        Workshop wk = new Workshop();
        wk.get_population_details_from_api();
        wk.get_population_details_from_xl();
     // Comment
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
 public void get_population_details_from_xl() throws IOException {

    System.out.println("**** RESPONSE FROM XL ****");
    String XLFilePath = "C:/Users/anshumanm/Downloads/Population_Details.xlsx";
    FileInputStream myxlfile = new FileInputStream(XLFilePath);
    XSSFWorkbook workbook = new XSSFWorkbook(myxlfile);
    XSSFSheet sheet = workbook.getSheetAt(0);
    int last_row = sheet.getLastRowNum();
        for (Row row : sheet)
        {
            if (row.getRowNum() != 0)
            {
                int year = (int) row.getCell(0).getNumericCellValue();
                int population = (int) row.getCell(1).getNumericCellValue();
                System.out.println("Year " +year+ " had population " +population);
            }

        }

 }
}
