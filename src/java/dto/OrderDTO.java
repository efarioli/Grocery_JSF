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
public class OrderDTO implements Serializable
{
    private int orderID;
    private java.sql.Timestamp dateTr;
    private int userID;

    public OrderDTO(int orderID, java.sql.Timestamp dateTr, int userID)
    {
        this.orderID = orderID;
        this.dateTr = dateTr;
        this.userID = userID;
    }

    public int getOrderID()
    {
        return orderID;
    }

    public void setOrderID(int orderID)
    {
        this.orderID = orderID;
    }

    public Timestamp getDateTr()
    {
        return dateTr;
    }

    public void setDateTr(Timestamp dateTr)
    {
        this.dateTr = dateTr;
    }

    public int getUserID()
    {
        return userID;
    }

    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    @Override
    public String toString()
    {
        return "OrderDTO{" + "orderID=" + orderID + ", dateTr=" + dateTr + ", userID=" + userID + '}';
    }
    
            
}
