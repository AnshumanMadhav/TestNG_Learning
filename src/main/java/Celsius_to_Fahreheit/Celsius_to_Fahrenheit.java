package Celsius_to_Fahreheit;
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
public class Celsius_to_Fahrenheit
{
    RegresJsonBody RJB1;
    public XmlPath xml_path_obj;
    @BeforeClass
    public void before_message()
    {
        RJB1 = new RegresJsonBody();
        System.out.println("Welcome to Before Class!!");
    }
    @Parameters({"celsius_to_fahrenheit_url","C_Temp","F_Temp"})
    @Test
    public void test_class(String c_url,String c_temp,String expected_f_temp)
    {
        Response rs = given().contentType(ContentType.XML).header("Content-Type","text/xml; charset=utf-8").body(RJB1.Convert_Celsius_to_Fahreheit(c_temp)).when().post(c_url);
        int status_code = rs.getStatusCode();
        String response_body = rs.getBody().asString();
        xml_path_obj = new XmlPath(rs.getBody().asString()).using(xmlPathConfig().namespaceAware(false));
        String actual_f_temp =  xml_path_obj.getString("soap:Envelope.soap:Body.CelsiusToFahrenheitResponse.CelsiusToFahrenheitResult");
        System.out.println(status_code);
        System.out.println(response_body);
        if(expected_f_temp.equals(actual_f_temp))
        {
            System.out.println("Value matches");
        }
        else
        {
            System.out.println("Value does not matches");
        }

    }
    @AfterClass
    public void after_message()
    {

        System.out.println("Welcome to After Class!!");
    }




}
