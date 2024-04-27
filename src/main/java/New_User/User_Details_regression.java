package New_User;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;

public class User_Details_regression
{
    Response response;
    //Declarations that helps to generate logs in the test report
    private ExtentSparkReporter spark;
    private ExtentReports extent;
    private ExtentTest logger;

    @BeforeClass
    public void createSetup()
    {
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/Report/Regression_Report.html");
        spark.config().setDocumentTitle("Regression Validation");
        spark.config().setReportName("Regres_Get_API_Details");
        spark.config().setTheme(Theme.DARK);
        logger = extent.createTest("Validate Regression GET API from Regres Application");
        extent.attachReporter(spark);
        extent.setSystemInfo("QA_Name","Anshuman");
        extent.setSystemInfo("Build_Name","Version_1.0");
        extent.setSystemInfo("Environment_Name","QA");

    }

    @Test
     public void get_user_details()
     {
         System.out.println("User Details for Regression Testing are as follows");
         logger.info("The Get API URL is" + "https://reqres.in/api/users/?page=2");
         response = get("https://reqres.in/api/users/?page=2");
         System.out.println(response.getBody().asString());
         int status_code = response.getStatusCode();
         Assert.assertEquals(String.valueOf(status_code),"200");
         logger.pass("The status code is as expected as "+status_code);
         int json_path_count = response.getBody().jsonPath().getList("data.first_name").size();
         System.out.println("Count is ==>" +json_path_count);

         for(int a = 0;a < json_path_count; a++)
         {
             String id = response.getBody().jsonPath().getString("data.id["+a+"]");
             String email = response.getBody().jsonPath().getString("data.email["+a+"]");
             System.out.println("If ID Value is ==>" +id+" Then Email is ==>" +email);
             logger.info("If ID Value is ==>" +id+" Then Email is ==>" +email);
         }
     }
     @AfterClass
     public void close_report()
     {
         extent.flush();
     }
}
