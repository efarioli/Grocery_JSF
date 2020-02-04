/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dbase.StoreGateway;
import dto.ProductDTO;
import dto.StoreDTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Inject;
import ui_admin.CommandFactory;

/**
 *
 * @author f023507i
 */
@Named(value = "storeBean")
@SessionScoped

public class StoreBean implements Serializable
{

    @Inject
    ShoppingCart shoppingCart;
    /**
     * Creates a new instance of StoreBean
     */
    private StoreDTO store = null;
    private ArrayList<StoreDTO> products = new ArrayList<>();

    public StoreBean()
    {
    }

    public ArrayList<StoreDTO> getStores()
    {
        StoreGateway stgw = new StoreGateway();
        return stgw.getAllStores();
    }

    public String setStoreForShoppingCart(int store)
    {
        shoppingCart.setStoreId(store);
        return "index.xhtml?faces-redirect=true";

    }
    public String showAllStores(){
        return "ChooseStore.xhtml";
    }

}
