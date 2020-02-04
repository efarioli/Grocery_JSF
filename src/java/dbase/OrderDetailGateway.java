package dbase;

import bean.ProductBean;
import dto.OrderDTO;
import dto.ProductDTO;
import dto.OrderDetailItemDTO;
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
public class OrderDetailGateway extends DB_Manager
{
    
    public boolean insertOrderDetail(int orderID, OrderDetailItemDTO scp, int storeID)
    {
        try
        {
            StockGateway sg = new StockGateway();
            int currentStock = sg.getProductStockInStore(scp.getProduct(), storeID);
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO ORDER_DETAIL("
                    + "ORDER_ID, DESCRIPTION, QUANTITY, IMG_URL, PRICE "
                    + ") VALUES (?,?,?,?,?)"
            );
            
            stmt.setInt(1, orderID);
            stmt.setString(2, scp.getProduct().getDescription());
            stmt.setInt(3, scp.getQty());
            stmt.setString(4, scp.getProduct().getImgURL());
            stmt.setFloat(5, scp.getProduct().getPrice());
            
            int numRowsInserted = stmt.executeUpdate();
            
            stmt.close();
            conn.close();
            
            sg.removeStockInStore(storeID, scp.getProduct().getId(), scp.getQty());
            System.out.println("===============================================");
            System.out.println("storeID " + storeID + " -- ORderID " + orderID + " --Qty " + scp.getQty());
            System.out.println("===============================================");
            return numRowsInserted == 1;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<OrderDetailItemDTO> getAllOrderDetailsByOrderID(OrderDTO order)
    {
        ArrayList<OrderDetailItemDTO> orderDetails = new ArrayList<>();
        
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ORDER_DETAIL  WHERE ORDER_ID = ?");
            stmt.setInt(1, order.getOrderID());
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {
                orderDetails.add(new OrderDetailItemDTO(
                        rs.getInt("QUANTITY"), new ProductDTO(
                        0,
                        rs.getString("DESCRIPTION"),
                        rs.getString("IMG_URL"),
                        rs.getFloat("PRICE"))
                ));
//                System.out.println("" + rs.getDate("DATE_TR"));
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return new ArrayList<OrderDetailItemDTO>();
        }
        System.out.println("LIST OF ORDERDETAIL FOR ORDER N " + order.getOrderID());
        for ( OrderDetailItemDTO o : orderDetails)
        {
            System.out.println(o);
        }
        return orderDetails;
    }
    
      public double getTotalByOrderID(OrderDTO order)
    {
        ArrayList<OrderDetailItemDTO> orderDetails = new ArrayList<>();
        double result = 0;
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ORDER_DETAIL  WHERE ORDER_ID = ?");
            stmt.setInt(1, order.getOrderID());
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {
                result += rs.getFloat("PRICE") * rs.getInt("QUANTITY");
                
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
        
        return result;
    }
    
}
