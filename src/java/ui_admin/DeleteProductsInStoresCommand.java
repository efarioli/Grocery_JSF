/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui_admin;

import dto.ProductDTO;
import manager.ProductManager;
import manager.StockManager;

/**
 *
 * @author f023507i
 */
public class DeleteProductsInStoresCommand implements Command
{

    private final StockManager stockMgr = new StockManager();
    private final ProductDTO product;

    public DeleteProductsInStoresCommand(ProductDTO product)
    {
       this.product = product;
    }

    @Override
    public Object execute()
    {
        stockMgr.deleteProductsInStores(product);
        return null;
    }

}
