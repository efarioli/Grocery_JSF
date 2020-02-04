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
public class StoreDTO implements Serializable
{
    private final int storeID;
    private final String storeName;
    private final String storeAddress;

    public StoreDTO(int storeID, String storeName, String storeAddress)
    {
        this.storeID = storeID;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
    }

    public int getStoreID()
    {
        return storeID;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public String getStoreAddress()
    {
        return storeAddress;
    }

    @Override
    public String toString()
    {
        return "StoreDTO{" + "storeID=" + storeID + ", storeName=" + storeName + ", storeAddress=" + storeAddress + '}';
    }
    
    
}
