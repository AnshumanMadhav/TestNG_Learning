package My_Learning;

public class Post_Req_Body
{
public static String aadhar_details(String firstname,String lastname,long aadhar,String address,long phone)
{
 String post_request_body = "{\n" +
            "\"Fname\": \""+firstname+"\",\n" +
            "\"Lname\": \""+lastname+"\",\n" +
            "\"Aadhar_No\": "+aadhar+",\n" +
            "\"Address\": \""+address+"\",\n" +
            "\"Phone\": "+phone+"\n" +
            "}";
  return post_request_body;
}
}
