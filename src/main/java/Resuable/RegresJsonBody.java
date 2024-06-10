package Resuable;

public class RegresJsonBody {
    public String CreateUserJsonBody(String name, String SSN) {
        String body = "{\n" +
                "    \"name\": \"" + name + "\",\n" +
                "    \"job\": \"API TEST AUTOMATION LEAD\",\n" +
                "    \"Address\": \"123 Dublin Blvd USA\",\n" +
                "    \"SSN\": \"" + SSN + "\"\n" +
                "}";
        return body;
    }

    public String Convert_Celsius_to_Fahreheit(String c_temp)
    {
        String request_body_001 =
                "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "  <soap12:Body>\n" +
                "    <CelsiusToFahrenheit xmlns=\"https://www.w3schools.com/xml/\">\n" +
                "      <Celsius>"+c_temp+ "</Celsius>\n" +
                "    </CelsiusToFahrenheit>\n" +
                "  </soap12:Body>\n" +
                "</soap12:Envelope>";
        return request_body_001;
    }
    public String User_Job(String name, String job) {
        String body = "{" +
                "    \"name\": \"" +name+ "\"," +
                "    \"job\": \""+job+"\"" +
                "}";
        return body;
    }
}

