/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.sql.*;

/**
 *
 * @author f023507i
 */
public class OrderPlusTotalDTO implements Serializable
{
    private OrderDTO order;
    private double total;

    public OrderPlusTotalDTO(OrderDTO order, double total)
    {
        this.order = order;
        this.total = total;
    }

    public OrderDTO getOrder()
    {
        return order;
    }

    public void setOrder(OrderDTO order)
    {
        this.order = order;
    }

    public double getTotal()
    {
        return total;
    }

    public void setTotal(double total)
    {
        this.total = total;
    }

    @Override
    public String toString()
    {
        return "OrderPlusTotalDTO{" + "order=" + order + ", total=" + total + '}';
    }

    
    
            
}
