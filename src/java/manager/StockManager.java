/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dbase.StockGateway;
import dto.ProductDTO;

/**
 *
 * @author f023507i
 */
public class StockManager
{
    private StockGateway stockTable = new StockGateway();
    public void deleteProductsInStores(ProductDTO product){
        stockTable.deleteProductsInStores(product);
    }
    public void updateStockInStore(int storeID, int productID, int qty){
        stockTable.addStockInStore(storeID, productID, qty);
    }
    
}
