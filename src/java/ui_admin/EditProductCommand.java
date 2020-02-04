/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui_admin;

import dto.ProductDTO;
import manager.ProductManager;

/**
 *
 * @author ezequ
 */
public class EditProductCommand implements Command
{
     private final ProductManager productMgr = new ProductManager();
    private final ProductDTO pr;

    public EditProductCommand(ProductDTO pr)
    {
        this.pr = pr;
    }

    @Override
    public Object execute()
    {
        productMgr.editProduct(pr);
        return null;
    }
}
