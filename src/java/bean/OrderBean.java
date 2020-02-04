/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dbase.OrderDetailGateway;
import dbase.OrderGateway;
import dto.OrderDTO;
import dto.OrderPlusTotalDTO;
import dto.ProductDTO;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import ui_admin.CommandFactory;

/**
 *
 * @author f023507i
 */
@Named(value = "orderBean")
@RequestScoped
public class OrderBean
{

    @Inject
    ShoppingCart shoppingCart;
     @Inject
    OrderDetailBEan orderDetailBEan;
    /**
     * Creates a new instance of OrderBean
     */
        // private ArrayList<OrderPlusTotalDTO> orderTotals = new ArrayList<>();

    public OrderBean()
    {
    }

    public ArrayList<OrderPlusTotalDTO> getOrderTotals()
    {
        ArrayList<OrderPlusTotalDTO> orderTotals = new ArrayList<>();
        OrderGateway og = new  OrderGateway();
       ArrayList<OrderDTO> orders = og.getAllOrdersByUser(shoppingCart.getUser().getId());
       for (OrderDTO o : orders){
           orderTotals.add(new OrderPlusTotalDTO(o, getTotal(o)));
       }
        return orderTotals;
    }
    
//    public ArrayList<OrderDTO> getOrders()
//    {
//       OrderGateway og = new  OrderGateway();
//       ArrayList<OrderDTO> orders = og.getAllOrdersByUser(shoppingCart.getUser().getId());
//       for (OrderDTO o : orders){
//           orderTotals.add(new OrderPlusTotalDTO(o, getTotal(o)));
//       }
//       
//       return orders;
//    }
    public double getTotal(OrderDTO order){
        OrderDetailGateway odg = new  OrderDetailGateway();
        return odg.getTotalByOrderID(order);
    }
    
}
