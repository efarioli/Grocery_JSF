package dbase;

import bean.ProductBean;
import dto.OrderDTO;
import dto.ProductDTO;
import dto.StoreDTO;
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
public class OrderGateway extends DB_Manager
{

    public int inserOrder(UserDTO user)
    {
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO ORDERS("
                    + "USER_ID "
                    + ") VALUES (?)",
                     Statement.RETURN_GENERATED_KEYS
            );

            stmt.setInt(1, user.getId());

            int numRowsInserted = stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            int auto_id = rs.getInt(1);
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@" + auto_id);

            stmt.close();
            conn.close();

            // return numRowsInserted == 1;
            return auto_id;
        } catch (SQLException e)
        {
            e.printStackTrace();
            //return false;
            return -1;
        }
    }

    public ArrayList<OrderDTO> getAllOrdersByUser(int userID)
    {
        ArrayList<OrderDTO> orders = new ArrayList<>();

        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ORDERS  WHERE USER_ID = ?");
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                orders.add(new OrderDTO(
                        rs.getInt("ORDER_ID"),
                        rs.getTimestamp("DATE_TR"),
                        rs.getInt("USER_ID")                        
                ));
                System.out.println("" + rs.getDate("DATE_TR") );
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return new ArrayList<OrderDTO>();
        }
        System.out.println("LIST OF ORDER FOR CUSTOMER N " + userID);
        for (OrderDTO o : orders){
            System.out.println(o);
        }
        return orders;
    }

}
