package dbase;

import bean.ProductBean;
import dto.ProductDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *
 * @author gdm1
 */
public class ProductGateway extends DB_Manager
{

    public ProductDTO findProduct(int id)
    {
        ProductDTO b = null;

        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PRODUCTS WHERE PRODUCT_ID = ?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                b = new ProductDTO(
                        rs.getInt("PRODUCT_ID"),
                        rs.getString("DESCRIPTION"),
                        rs.getString("IMG_URL"),
                        rs.getFloat("PRICE")
                );
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return b;
    }

    public ArrayList<ProductDTO> getAllProducts()
    {
        ArrayList<ProductDTO> products = new ArrayList<>();

        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PRODUCTS ORDER BY DESCRIPTION");

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                products.add(new ProductDTO(
                        rs.getInt("PRODUCT_ID"),
                        rs.getString("DESCRIPTION"),
                        rs.getString("IMG_URL"),
                        rs.getFloat("PRICE")
                ));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return new ArrayList<ProductDTO>();
        }

        return products;
    }

    public boolean insertProduct(ProductDTO product)
    {
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO PRODUCTS("
                    + "DESCRIPTION, IMG_URL, PRICE "
                    + ") VALUES (?,?, ?)"
            );

            stmt.setString(1, product.getDescription());
            stmt.setString(2, product.getImgURL());
            stmt.setDouble(3, product.getPrice());

            int numRowsInserted = stmt.executeUpdate();

            stmt.close();
            conn.close();

            return numRowsInserted == 1;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public void updatePriceinProduct(double price, ProductDTO product)
    {
        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE PRODUCTS  SET PRICE = ? WHERE PRODUCT_ID = ?"
            );

            stmt.setDouble(1, product.getPrice());
            stmt.setInt(2, product.getId());

            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (SQLException e)
        {
            e.printStackTrace();

        }
    }

    public void editProduct(ProductDTO pr)
    {
        try
        {
            Connection conn = getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PRODUCTS WHERE PRODUCT_ID = ?");
            stmt.setInt(1, pr.getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {

                ProductBean objCustomer = new ProductBean();

                objCustomer.setId(pr.getId());
                objCustomer.setDescription(rs.getString("DESCRIPTION"));
                objCustomer.setImgURL(rs.getString("IMG_URL"));
                objCustomer.setPrice(rs.getFloat("PRICE"));

                rs.next();

            }

            stmt.close();
            conn.close();
        } catch (SQLException sqle)
        {
            System.out.println(sqle.getMessage());
        }
    }

    public void updateProduct(ProductDTO pr)
    {
        try
        {
            System.out.println("==============================================");
            System.out.println(pr);
            System.out.println("==============================================");
            Connection conn = getConnection();

//            PreparedStatement stmt = conn.prepareStatement("UPDATE PRODUCTS"
//                    + " SET  "
//                  + "   IMG_URL = ? ,"
//                    + "   PRICE = ? "
//                    + " WHERE PRODUCT_ID = ?");
//            
//            stmt.setString(1, pr.getDescription());
//            stmt.setString(2, pr.getImgURL());
//            stmt.setDouble(3, pr.getPrice());
//            //stmt.setInt(4, id2);
//            stmt.setInt(4, pr.getId());
//            stmt.executeUpdate();
            PreparedStatement stmt = conn.prepareStatement("UPDATE PRODUCTS"
                    + " SET  "
                    
                    + "   PRICE = ? "
                    + " WHERE PRODUCT_ID = ?");

      
            stmt.setDouble(1, pr.getPrice());
            stmt.setInt(2, pr.getId());
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        } catch (SQLException sqle)
        {
            System.out.println(sqle.getMessage());
        }
    }
}
