package Rest_Assured_Assignments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class jdbc_learning
{
    public static void main(String[] args)
    {

    }
    public void update_records(String id)
    {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "root", "tiger");
            String before_update_query = "select * from db_created_through_ij.employee_2 where id = '"+id+"';";
            PreparedStatement ps = con.prepareStatement(before_update_query);
            ps.executeQuery();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
