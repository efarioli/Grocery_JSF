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
public class OrderDetailItemDTO implements Serializable
{
    private int qty;
    private ProductDTO product;
    

    public OrderDetailItemDTO(int qty, ProductDTO product)
    {
        this.qty = qty;
        this.product = product;
    }

    
    public int getQty()
    {
        return qty;
    }

    public void setQty(int qty)
    {
        this.qty = qty;
    }

    public ProductDTO getProduct()
    {
        return product;
    }

    public void setProduct(ProductDTO product)
    {
        this.product = product;
    }

    @Override
    public String toString()
    {
        return "ShoppingCartProduct{" + "qty=" + qty + ", product=" + product + '}';
    }
    
    
}
