/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dbase.StockGateway;
import dto.ProductDTO;
import dto.StoreDTO;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import manager.ProductManager;
import ui_admin.CommandFactory;

/**
 *
 * @author f023507i
 */
@Named(value = "stockBean")
@RequestScoped
public class StockBean
{

    /**
     * Creates a new instance of StockBean
     */
    private int storeID;
    private int productId;
    private int qty;
    private Map<String, Object> requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
    private ProductDTO productObj = new ProductDTO(0, "", "", 0);

    public ProductDTO getProductObj()
    {
        return productObj;
    }

    public void setProductObj(ProductDTO productObj)
    {
        this.productObj = productObj;
    }

    public StockBean()
    {
    }

    public String editQuantityStock(ProductDTO product)
    {       

        setProductObj(product);

        return "/admin/edit_quantity_in_Store.xhtml"; // dont put redirect
    }

    public String updateQtyInStore(int storeID, int productID, int qty)
    {
        
         new CommandFactory()
                .createCommand(CommandFactory.UPDATE_QTY_IN_STORE, storeID, productID, qty)
                .execute();    

        

        return "/admin/ViewAllProducts.xhtml?faces-redirect=true";
    }

    public StockBean(int storeID, int productId, int qty)
    {
        this.storeID = storeID;
        this.productId = productId;
        this.qty = qty;
    }

    public int getStoreID()
    {
        return storeID;
    }

    public void setStoreID(int storeID)
    {
        this.storeID = storeID;
    }

    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public int getQty()
    {
        return qty;
    }

    public void setQty(int qty)
    {
        this.qty = qty;
    }

    public String deleteProductsInStores(ProductDTO product)
    {
        
        new CommandFactory()
                .createCommand(CommandFactory.DELETE_PRODUCTS_IN_STORES, product)
                .execute();        


        return "/admin/ViewAllProducts.xhtml?faces-redirect=true";
    }
    
    public int  getStockinStore (int storeID, int productID){
        StockGateway sg = new StockGateway();
        int res = sg.getStockInStore(storeID, productID);
        if (res==-9999){
            res =0;
        }
        return res;
        
    }
//    public int  getStockinStore ( int productID){
//        
//    }
}
