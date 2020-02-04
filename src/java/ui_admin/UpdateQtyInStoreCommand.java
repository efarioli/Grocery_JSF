/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui_admin;

import manager.StockManager;

/**
 *
 * @author f023507i
 */
public class UpdateQtyInStoreCommand implements Command
{
    private final StockManager stockMgr = new StockManager();
    private final int storeID;
    private final int productID;
    private final int qty;

    public UpdateQtyInStoreCommand(int storeID, int productID, int qty)
    {
        this.storeID =  storeID;
        this.productID = productID;
        this.qty = qty;
    }
    @Override
    public Object execute()
    {
        stockMgr.updateStockInStore(storeID, productID, qty);
        return null;
    }
    
}
