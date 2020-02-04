package dbase;

import bean.ProductBean;
import dto.ProductDTO;
import dto.StoreDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *
 * @author gdm1
 */
public class StockGateway extends DB_Manager
{

    public int getStockInStore(int storeID, int productID)
    {
        //if I dont have any row, the stock is zero, the retuned value is -9999
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT STORES.STORE_NAME, PRODUCTS.DESCRIPTION, "
                    + "STOCK.QUANTITY FROM STOCK JOIN PRODUCTS ON STOCK.PRODUCT_ID = PRODUCTS.PRODUCT_ID "
                    + "JOIN STORES  ON STORES.STORE_ID = STOCK.STORE_ID WHERE STORES.STORE_ID = ? AND PRODUCTS.PRODUCT_ID = ?");
            stmt.setInt(1, storeID);
            stmt.setInt(2, productID);

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                return rs.getInt("QUANTITY");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return -9999;
        }
        return -9999;
    }

    public void addStockInStore(int storeID, int productID, int qty)
    {
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt;
            int stockbeforeUpdate = getStockInStore(storeID, productID);
            if (stockbeforeUpdate == -9999)
            {
                stmt = conn.prepareStatement("INSERT INTO STOCK(STORE_ID, PRODUCT_ID, QUANTITY)  VALUES(?, ?, ?)");
                stmt.setInt(1, storeID);
                stmt.setInt(2, productID);
                stmt.setInt(3, qty);
                stmt.executeUpdate();
            } else
            {

                stmt = conn.prepareStatement("UPDATE STOCK  SET QUANTiTY = ? WHERE STORE_ID = ? AND PRODUCT_ID = ?");
                stmt.setInt(1, (qty + stockbeforeUpdate));
                stmt.setInt(2, storeID);
                stmt.setInt(3, productID);
                stmt.executeUpdate();

            }
            stmt.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void removeStockInStore(int storeID, int productID, int qty)
    {
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt;
            stmt = conn.prepareStatement("UPDATE STOCK  SET QUANTiTY = ? WHERE STORE_ID = ? AND PRODUCT_ID = ?");

            int stockbeforeUpdate = getStockInStore(storeID, productID);
            if (stockbeforeUpdate == -9999)
            {

                System.out.println("no rows for tha product in that store");
            } else
            {

                stmt.setInt(1, (stockbeforeUpdate - qty));
                stmt.setInt(2, storeID);
                stmt.setInt(3, productID);
                stmt.executeUpdate();

            }
            stmt.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteProductsInStores(ProductDTO product)
    {
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt;

            stmt = conn.prepareStatement("DELETE FROM STOCK WHERE PRODUCT_ID=?");
            stmt.setInt(1, product.getId());
            int row = stmt.executeUpdate();
            System.out.println("Stock colums: " + row);

            stmt = conn.prepareStatement("DELETE FROM PRODUCTS WHERE PRODUCT_ID=?");
            stmt.setInt(1, product.getId());
            row = stmt.executeUpdate();
            System.out.println("Products colums: " + row);

            stmt.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public int getProductStockInStore(ProductDTO product, int storeID)
    {
        int stock;

        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM STOCK WHERE PRODUCT_ID = ? AND STORE_ID = ?");
            stmt.setInt(1, product.getId());
            stmt.setInt(2, storeID);

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                return rs.getInt("QUANTITY");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
        //no row result, so there is not product in that particuale store
        return 0;
    }

}
