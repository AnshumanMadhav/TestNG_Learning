package readXL;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class support
{
    public static void main(String[] args)
    {

    }
    public String read_and_print_xl_as_per_test_data(String Test_case_name,String columnName)
    {
       String data = null;
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
                            data = sheet.getRow(i).getCell(j).toString();
                            System.out.println("The XL Value is:" +data);
                        }

                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return data;
    }

    public String convert_status_code_to_string(String status_code)
    {
      String FLAG = null;
      try
      {
          float f;
          int val;
          f = Float.parseFloat(status_code);
          val = (int)f;
          FLAG = String.valueOf(FLAG);
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      return FLAG;
    }

}
