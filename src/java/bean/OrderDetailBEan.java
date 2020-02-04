/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dbase.OrderDetailGateway;
import dbase.OrderGateway;
import dto.OrderDTO;
import dto.OrderDetailItemDTO;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author f023507i
 */
@Named(value = "orderDetailBEan")
@RequestScoped
public class OrderDetailBEan
{

    @Inject
    OrderBean orderBean;
    /**
     * Creates a new instance of OrderDetailBEan
     */
    private OrderDTO order = null;
    
    private ArrayList<OrderDetailItemDTO> al = new ArrayList<>();

    public OrderDTO getOrder()
    {
        return order;
    }

    public void setOrder(OrderDTO order)
    {
        this.order = order;
    }
    
    public OrderDetailBEan()
    {
    }

    public ArrayList<OrderDetailItemDTO> getOrderDetails()
    {
        //this.order = order;
        OrderDetailGateway odg = new OrderDetailGateway();
        al = odg.getAllOrderDetailsByOrderID(order);
        return al;
    }
    public String webOrderDetail (OrderDTO order){
        this.order = order;
        return "SeeOrderDetail.xhtml";
    }
    public double getTotal (){
        double result = 0;
        for (OrderDetailItemDTO or : al){
            result += or.getQty() * or.getProduct().getPrice();
        }
        return result;
    }
}
