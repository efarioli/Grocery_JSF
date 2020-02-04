/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author f023507i
 */
public class StockDTO implements  Serializable
{
    private final int storeID;
    private final int productID;
    private final int qty;

    public StockDTO(int storeID, int productID, int qty)
    {
        this.storeID = storeID;
        this.productID = productID;
        this.qty = qty;
    }

    public int getStoreID()
    {
        return storeID;
    }

    public int getProductID()
    {
        return productID;
    }

    public int getQty()
    {
        return qty;
    }
    
    
}
