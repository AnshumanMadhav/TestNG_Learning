package My_Learning;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Read_Property_File_Data
{
  public static String read_properties(String key)
  {
    Properties prop = new Properties();
    String value = null;
    try
      {
          prop.load(new FileInputStream(System.getProperty("user.dir")+"/capstone_project_data.properties"));
          value = prop.getProperty(key);
      }
      catch (IOException e)
      {
          e.printStackTrace();
      }
      return value;
  }
}
