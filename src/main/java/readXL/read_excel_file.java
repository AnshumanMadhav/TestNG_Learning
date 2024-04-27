package readXL;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;

public class read_excel_file
{
    public static void main(String[] args)
    {
     read_and_print_xl_as_per_test_data("TC002","Title");
    }
 //
 public void read_and_print_simple_xl()
 {
     //INTERFACES INSIDE APACHE POI

     // Workbook : Interface to instantiate different XL Files (xls/xlsx)
     // Sheet    : Interface to read the sheet inside the workbook
     // Row      : Interface to identify the row inside the sheet
     // Cell     : Interface to identify the cell inside the row

     //CLASSES INSIDE APACHE POI

     //XSSF Workbook : Class which will implement Interface for the XL File.
     //HSSF Workbook : Class which will implement Interface for the XL File.
     //XSSF Sheet    : Class representing a sheet interface.
     //HSSF Sheet    : Class representing a sheet interface.
     //XSSF Row      : Class representing a row interface.
     //HSSF Row      : Class representing a row interface.
     //XSSF Cell     : Class representing a cell interface.
     //HSSF Cell     : Class representing a cell interface.

     try
     {
         String XLFilePath = "C:/Users/anshumanm/Downloads/Excel_Test_Data.xlsx";
         FileInputStream myxlfile = new FileInputStream(XLFilePath);
         Workbook workbook = new XSSFWorkbook(myxlfile);
         Sheet sheet = workbook.getSheet("Test_Data");
         int last_row = sheet.getLastRowNum();
         System.out.println("The last row which has data ==> " +last_row);

         //Loop for Row Iteration
         for (int i =1;i<=last_row;i++)
         {
             Row row = sheet.getRow(i);

             //Get Last column which has data
             int last_cell = row.getLastCellNum();

             for (int j =0; j< last_cell; j++)
             {
                 Cell cell = row.getCell(j);
                 String value = cell.getStringCellValue();
                 System.out.println("The XL Value is:" +value);
             }
         }
     }
     catch (Exception e)
     {
        e.printStackTrace();
     }
 }
 public static void read_and_print_xl_as_per_test_data(String Test_case_name,String columnName)
 {

     try
     {
         String XLFilePath = "C:/Users/anshumanm/Downloads/Excel_Test_Data.xlsx";
         FileInputStream myxlfile = new FileInputStream(XLFilePath);
         Workbook workbook = new XSSFWorkbook(myxlfile);
         Sheet sheet = workbook.getSheet("URL_Data");
         int last_row = sheet.getLastRowNum();
        // System.out.println("The last row which has data ==> " +last_row);

         //Loop for Row Iteration
         for (int i =0;i<=last_row;i++)
         {
             Row row = sheet.getRow(i);

             //Get Last column which has data
             int last_cell = row.getLastCellNum();
             Cell cell = row.getCell(0);
             String runtimeTCName = cell.getStringCellValue();
            // System.out.println("The First Column Value is:" +runtimeTCName);
             if(runtimeTCName.equals(Test_case_name))
             {
                 Row row_new = sheet.getRow(0);
             for (int j =0; j< last_cell; j++)
             {
                 Cell cell_new = row_new.getCell(j);
                 String runtime_cell_value = cell_new.getStringCellValue();
                 if(runtime_cell_value.equals(columnName))
                 {
                     String value = sheet.getRow(i).getCell(j).toString();
                     System.out.println("The XL Value is:" +value);
                 }

             }
             }
         }
     }
     catch (Exception e)
     {
         e.printStackTrace();
     }
 }
}
