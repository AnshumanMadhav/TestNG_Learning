package Resuable;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class common
{
    public String read_properties(String key)
    {
        Properties prop = new Properties();
        String value = null;
        try
        {
            prop.load(new FileInputStream(System.getProperty("user.dir")+"/testdata.properties"));
            value = prop.getProperty(key);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return value;
    }
}
