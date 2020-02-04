package dbase;

import bean.ProductBean;
import dto.ProductDTO;
import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *
 * @author gdm1
 */
public class AdminUserGateway extends DB_Manager
{
    public boolean isUsernameInDB(String userName)
    {
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM USERS WHERE U_NAME = ?");
            stmt.setString(1, userName);

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                return true;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }
     public int insertUser(UserDTO user)
    {
        //need number of user of newly created user
        //to be able to Register and Login at once
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO USERS("
                    + "NAME, U_NAME, PASSWORD, ADDRESS "
                    + ") VALUES (?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS
            );

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getUname());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getAddress());
            int numRowsInserted = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            int auto_id = rs.getInt(1);

            stmt.close();
            conn.close();

            return auto_id;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }
     
     
   
}
