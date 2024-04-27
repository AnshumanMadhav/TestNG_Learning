package New_User;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.path.xml.config.XmlPathConfig.xmlPathConfig;

public class Validate_Book_Details
{
    Response response;
    public XmlPath xml_path_obj;
    @BeforeClass
    public void display_before_message()
    {
        System.out.println("We are in Before Class of Validate Book Details!");
    }
    @Parameters({"library_book_url","Book_Category","Book_Title","Book_Author","Year","Price"})
    @Test(groups = "regression")
    public void book_details_validation(String library_book_url,String expected_Book_Category,String expected_Book_Title,String expected_book_author,String expected_year,String expected_book_price)
    {
        response = get(library_book_url);
        xml_path_obj = new XmlPath(response.getBody().asString()).using(xmlPathConfig().namespaceAware(false));
        String actual_book_category = xml_path_obj.getString("bookstore.book[1].@category");
        String actual_book_title = xml_path_obj.getString("bookstore.book[1].title");
        String actual_author = xml_path_obj.getString("bookstore.book[1].author");
        String actual_year = xml_path_obj.getString("bookstore.book[1].year");
        String actual_price = xml_path_obj.getString("bookstore.book[1].price");
        Assert.assertEquals(expected_Book_Category,actual_book_category);
        Assert.assertEquals(expected_Book_Title,actual_book_title);
        Assert.assertEquals(expected_book_author,actual_author);
        Assert.assertEquals(expected_year,actual_year);
        Assert.assertEquals(expected_book_price,actual_price);
    }
    @AfterClass
    public void display_after_message()
    {
        System.out.println("We are in After Class of Validate Book Details!");
    }
}
