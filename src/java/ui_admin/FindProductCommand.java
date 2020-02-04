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
 * @author f023507i
 */
public class FindProductCommand implements Command
{

    private final int prodID;
    private final ProductManager productMgr;

    public FindProductCommand(int productID)
    {
        this.prodID = productID;
        productMgr = new ProductManager();
    }

    @Override
    public Object execute()
    {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println(productMgr.findProduct(prodID).getDescription());
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        productMgr.findProduct(prodID);
        return null;
    }

}
