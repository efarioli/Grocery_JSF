package ui_admin;

import dto.ProductDTO;
import manager.ProductManager;

/**
 *
 * @author gdm1
 */
public class AddProductCommand implements Command
{
    private final ProductManager productMgr = new ProductManager();
    private final ProductDTO product;

    public AddProductCommand(ProductDTO product)
    {
        this.product = product;
    }

    @Override
    public Object execute()
    {
        productMgr.addProduct(product);
        return null;
    }
    
}
