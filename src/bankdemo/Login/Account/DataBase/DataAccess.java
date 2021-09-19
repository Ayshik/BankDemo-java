package bankdemo.Login.Account.DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataAccess{
    Connection  conn;
    String J_Driver;
    String DB_URL;
    String USER,PASS;
    Statement stmt;
    ResultSet rs;
    public DataAccess()
    {
        J_Driver="com.mysql.cj.jdbc.Driver";
        DB_URL="jdbc:mysql://localhost:3306/test";
        USER="root";
        PASS="";

        try{
            Class.forName(J_Driver);
            System.out.println("Connecting to Database...");
            conn=DriverManager.getConnection(DB_URL,USER,PASS);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void close()throws SQLException{
        if(rs!=null)rs.close();
        if(rs!=null)stmt.close();
    }
    public ResultSet getData(String query){
        try{
            stmt=conn.createStatement();
            rs=stmt.executeQuery(query);
        }
        catch(Exception ex)
        {
            System.out.println("DataBase Read Error!");
        }
        return rs;
    }
    public int updateDB(String sql){
        int numofRowsUpdated=0;
        try{
            stmt=conn.createStatement();
            numofRowsUpdated=stmt.executeUpdate(sql);
            System.out.println(numofRowsUpdated+" rows updated");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return numofRowsUpdated;
    }
}