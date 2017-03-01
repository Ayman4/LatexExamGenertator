/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package latexexamgenertator;

/**
 *
 * @author ASUS
 */
import java.sql.*;
public class DBConnect {
    public static Connection con;
    //public static Statement st;
    //public static ResultSet rs;
    public static void ExecuteInsertquery(String sql) throws SQLException
    {
        Statement s=con.createStatement();
        s.executeUpdate(sql);
        
    }
    public static ResultSet ExecuteMyquery(String sql) throws SQLException
    {
        ResultSet r;
        Statement s=con.createStatement();
        r=s.executeQuery(sql);
        return r;
    }
    public DBConnect()
    {
        try
        {
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/latexexamjava","root","");
        
        
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
