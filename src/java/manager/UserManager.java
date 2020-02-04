package manager;

import dbase.UserGateway;
import dto.ProductDTO;
import dto.UserDTO;
import java.util.ArrayList;

/**
 *
 * @author zeke
 */
public class UserManager
{

    private UserGateway userTable = new UserGateway();
        

    public void addUser(UserDTO user)
    {

        userTable.insertUser(user);

    }

//    public ProductDTO findProduct(int id)
//    {        
//        return productTable.findProduct(id);
//    }
//
//    public ArrayList<ProductDTO> getAllProducts()
//    {
//        return productTable.getAllProducts();
//    }
//
//    public void changePriceProduct(double price, ProductDTO pr)
//    {
//        productTable.updatePriceinProduct(price, pr);
//    }
//
//    public void editProduct(ProductDTO pr)
//    {
//        productTable.editProduct(pr);
//    }
//
//    public void updateProduct(ProductDTO pr)
//    {
//         productTable.updateProduct(pr);
//    }
}
