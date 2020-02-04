package dbase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author zekeFarioli
 */
public class DB_Manager
{

    protected Connection getConnection()
    {
        try
        {

            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            System.out.println("CONECTION");
            //return DriverManager.getConnection("jdbc:derby://localhost:1527/grocery22", "emp", "emp");
            return DriverManager.getConnection("jdbc:derby://localhost:1527/grocery", "emp", "emp");

        } catch (SQLException e)
        {
            System.out.println("#########################NO CONECTION");

            return null;
        }
    }
}
