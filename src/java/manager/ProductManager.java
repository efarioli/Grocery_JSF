package manager;

import dbase.ProductGateway;
import dto.ProductDTO;
import java.util.ArrayList;

/**
 *
 * @author zeke
 */
public class ProductManager
{

    private ProductGateway productTable = new ProductGateway();
        

    public void addProduct(ProductDTO product)
    {

        productTable.insertProduct(product);

    }

    public ProductDTO findProduct(int id)
    {        
        return productTable.findProduct(id);
    }

    public ArrayList<ProductDTO> getAllProducts()
    {
        return productTable.getAllProducts();
    }

    public void changePriceProduct(double price, ProductDTO pr)
    {
        productTable.updatePriceinProduct(price, pr);
    }

    public void editProduct(ProductDTO pr)
    {
        productTable.editProduct(pr);
    }

    public void updateProduct(ProductDTO pr)
    {
         productTable.updateProduct(pr);
    }
}
