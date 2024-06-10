package My_Learning;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test
{
    public static void main(String[] args)
    {
      string_numeric_check("98765");
      string_numeric_check("A98765");
      SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd");
      Calendar c = Calendar.getInstance();
      System.out.println("Present Date : " + c.getTime());

      // Formatting Date according "dd / MM / yy"
      String formattedDate = sf.format(c.getTime());
      System.out.println("Date formatted : "+formattedDate);
      String dateTimeStr = "2024-06-07T09:51:43.051Z";
      String dateStr = extractDate(dateTimeStr);
      System.out.println("Extracted Date: " + dateStr);
    }
    public static void string_numeric_check(String test_string)
    {
      if(test_string.matches("[0-9]+"))
      {
        System.out.println("String contains only numbers");
      }
      else
      {
        System.out.println("String contains numbers and some other characters");
      }
    }
    public static String extractDate(String dateTimeStr) {
        // Split the string based on 'T' character and take the first part
        String[] parts = dateTimeStr.split("T");
        if (parts.length > 0) {
            return parts[0];
        }
        return null;
    }
}
