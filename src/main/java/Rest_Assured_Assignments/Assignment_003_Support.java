package Rest_Assured_Assignments;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class Assignment_003_Support
{

    public static void main(String[] args)
    {
        read_xl();
    }

    public static void read_xl()
    {
        try
        {
          String XLFilePath = "C:/Users/anshumanm/Downloads/Bank_Employee_Details.xlsx";
          FileInputStream myxlfile = new FileInputStream(XLFilePath);
          Workbook workbook = new XSSFWorkbook(myxlfile);
          Sheet sheet = workbook.getSheet("Employee_Details");

          int last_row = sheet.getLastRowNum();
          for (int i =1;i<=last_row;i++)
          {
              Row row = sheet.getRow(i);
              String name = "";
              int year = 0;
              int last_cell = row.getLastCellNum();
              for (int j =0; j< last_cell; j++)
              {
                Cell cell = row.getCell(j);
                if (cell != null)
                {
                    if (j == 0)
                    {
                        name = cell.toString();
                    } else if (j == 1) {
                        {
                          year = (int) cell.getNumericCellValue();
                        }
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
