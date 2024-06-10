package My_Learning;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import static My_Learning.Read_Property_File_Data.read_properties;

public class insert_records_from_xl
{
  static Connection connection;
  public static void insert_records() throws IOException, SQLException
  {
      FileInputStream fis = new FileInputStream(new File("C:/Users/anshumanm/OneDrive - Maveric Systems Limited/Desktop/API TESTING TRAINING/Capstone_Project/Aadhar_Details.xlsx"));
      XSSFWorkbook wb = new XSSFWorkbook(fis);
      XSSFSheet sheet = wb.getSheetAt(0);
      String url = read_properties("url");
      String user = read_properties("user");
      String password = read_properties("password");
      connection = DriverManager.getConnection(url,user,password);
      Statement stmt = connection.createStatement();
      for (Row row : sheet)
      {
          if (row.getRowNum() != 0)
          {
           String Fname = row.getCell(0).getStringCellValue();
           String Lname = row.getCell(1).getStringCellValue();
           long aadhar_no = (long)row.getCell(2).getNumericCellValue();
           String address = row.getCell(3).getStringCellValue();
           long phone_no = (long) row.getCell(4).getNumericCellValue();
           System.out.println(Fname + " "+ Lname + " " +aadhar_no + " " +address + " " +phone_no);
           String query = "insert into maven_practise.aadhar_details values (\""+Fname+"\",\""+Lname+"\","+aadhar_no+",\""+address+"\","+phone_no+")";
           System.out.println(query);
           stmt.execute(query);
          }
      }
      System.out.println("Records Inserted Successfully");
  }
}
