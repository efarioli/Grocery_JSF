package ui_admin;

import manager.ProductManager;

/**
 *
 * @author gdm1
 */
public class ViewAllProductCommand implements Command
{
    private final ProductManager productMgr = new ProductManager();

    @Override
    public Object execute()
    {
        return productMgr.getAllProducts();
    }
    
}
