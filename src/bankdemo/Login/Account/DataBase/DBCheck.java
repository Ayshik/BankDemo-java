/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankdemo.Login.Account.DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import bankdemo.Login.Account.DataBase.*;
public class DBCheck {
    public DBCheck()
    {
        DataAccess da=new DataAccess();
        String qs="select trid from transaction";
        try
        {
            ResultSet rs=da.getData(qs);
            while(rs.next())
            {
                System.out.println(rs.getInt(1));
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
