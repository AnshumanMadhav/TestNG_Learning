package readXL;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.get;

import org.testng.annotations.Test;
import readXL.support;

public class Web_API
{
  String testname = null;
  String url;
  String status_code;
  String title;
  support obj;

  @BeforeClass
  public void setup()
  {
    obj = new support();
  }
  @Test
  public void do_all_web_api_test()
  {

      testname = "TC001";
      url = obj.read_and_print_xl_as_per_test_data(testname,"RequestURL").trim();
      status_code = obj.convert_status_code_to_string(obj.read_and_print_xl_as_per_test_data(testname,"StatusCode"));
      System.out.println("The XL Status Code ==" +status_code);
      title = obj.read_and_print_xl_as_per_test_data(testname,"Title").trim();
      do_web_api_validation();
  }
  public void do_web_api_validation()
  {
      Response response = get(url);
      String res = response.getBody().asString();
      Assert.assertEquals(String.valueOf(response.getStatusCode()),status_code);
      Assert.assertTrue(res.contains(title));
  }
}
