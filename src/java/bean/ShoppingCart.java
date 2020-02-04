/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dbase.OrderDetailGateway;
import dbase.OrderGateway;
import dto.ProductDTO;
import dto.OrderDetailItemDTO;
import dto.UserDTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import ui_admin.CommandFactory;

/**
 *
 * @author f023507i
 */
@Named(value = "shoppingCart")
@SessionScoped
public class ShoppingCart implements Serializable {

    @Inject
    StoreBean storeBean;
    @Inject
    ProductBean productBean;

    /**
     * Creates a new instance of ShoppingCart
     */
    private int storeId = 0;

    private UserDTO user = null;

    private ArrayList<OrderDetailItemDTO> productsSC = new ArrayList<>(); //SC means ShopingCart

    private String whereWasCalled;
    
    private String previousURI = "index.xhtml";
    private String currentURI = "index.xhtml";

    public ShoppingCart() {
    }

    public String getPreviousURI() {
        return previousURI;
    }

    public void setPreviousURI(String previousURI) {
        this.previousURI = previousURI;
    }

    public String getCurrentURI() {
        return currentURI;
    }

    public void setCurrentURI(String currentURI) {
        this.currentURI = currentURI;
    }

    public String getWhereWasCalled() {
        return whereWasCalled;
    }

    public String clearBasket() {
        productsSC = new ArrayList<>();
        return "index.xhtml?faces-redirect=true";
    }

    public void setWhereWasCalled(String whereWasCalled) {
        this.whereWasCalled = whereWasCalled;
    }

    public UserDTO getUser() {
        System.out.println("USER" + (user == null));
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public float getTotal() {
        float result = 0;
        for (OrderDetailItemDTO scp : productsSC) {
            result += (float) (scp.getQty() * scp.getProduct().getPrice());
        }
        return result;
    }

    public ArrayList<OrderDetailItemDTO> getProductsSC() {
        return productsSC;
    }

    public ArrayList<OrderDetailItemDTO> getShoppingProducts() {
        ArrayList<ProductDTO> products = productBean.getProducts();
        for (ProductDTO p : products) {
            productsSC.add(new OrderDetailItemDTO(0, p));

        }
        return productsSC;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String buy() {
//        if(user == null){
//            setWhereWasCalled("Buy.xhtml");
//            return "Login.xhtml";
//            
//        }
        OrderGateway og = new OrderGateway();
        OrderDetailGateway odg = new OrderDetailGateway();
        int orderID = og.inserOrder(user);
        for (OrderDetailItemDTO scp : productsSC) {
            odg.insertOrderDetail(orderID, scp, storeId);
        }
        productsSC = new ArrayList<>();
        FacesContext.getCurrentInstance().addMessage("usermain:usermain", new FacesMessage("Login credentials are not correct. Please try again"));
        return "index.xhtml?faces-redirect=true";

    }

    public String removeOrderItem(OrderDetailItemDTO orderItem) {
        int index = findShoppingCartProduct(orderItem.getProduct());
        OrderDetailItemDTO scp = productsSC.get(index);

        productsSC.remove(index);
        if (productsSC.size() > 0) {
            return "SeeBasket.xhtml";

        } else {
            return "index.xhtml?faces-redirect=true";

        }

    }

    public String increaseProductQty(ProductDTO product) {

        int index = findShoppingCartProduct(product);
        OrderDetailItemDTO scp = productsSC.get(index);

        scp.setQty(scp.getQty() + 1);
        if (scp.getQty() == 0) {
            productsSC.remove(index);
        }

        printShoppingCart();

        return "SeeBasket.xhtml";
    }

    public String decreaseProductQty(ProductDTO product) {

        int index = findShoppingCartProduct(product);
        OrderDetailItemDTO scp = productsSC.get(index);

        scp.setQty(scp.getQty() - 1);
        if (scp.getQty() == 0) {
            productsSC.remove(index);
        }

        if (productsSC.size() == 0) {
            return "index.xhtml?faces-redirect=true";
        }

        printShoppingCart();

        return "SeeBasket.xhtml";
    }

    public String addOneProduct(ProductDTO product) {

        if (storeId == 0) {
            return "ChooseStore.xhtml?faces-redirect=true";

            // storeBean.showAllStores();
            //System.out.println("storeID is 0");
        }
        int index = findShoppingCartProduct(product);
        if (index == -1) {
            OrderDetailItemDTO scp1 = new OrderDetailItemDTO(1, product);
            productsSC.add(scp1);
        } else {
            productsSC.get(index).setQty(productsSC.get(index).getQty() + 1);
        }
        printShoppingCart();
        return "index.xhtml";
    }

    public String removeOneProduct(ProductDTO product) {
        if (storeId == 0) {
            return "ChooseStore.xhtml";

            // storeBean.showAllStores();
            //System.out.println("storeID is 0");
        }

        int index = findShoppingCartProduct(product);
        if (index != -1) {
            int prodQty = productsSC.get(index).getQty();

            if (prodQty > 1) {
                productsSC.get(index).setQty(productsSC.get(index).getQty() - 1);
            } else {
                productsSC.remove(index);
            }

        }
        printShoppingCart();
        return "index.xhtml?faces-redirect=true";
    }

    public int getProductQtyInCart(ProductDTO product) {
        int qty;
        int index = findShoppingCartProduct(product);
        if (index == -1) {
            qty = 0;

        } else {
            qty = productsSC.get(index).getQty();
        }
        printShoppingCart();
        return qty;
    }

    public float getProductSubtotal(ProductDTO product) {
        float subtotal;
        int qty = getProductQtyInCart(product);
        subtotal = (float) (product.getPrice() * qty);
        return subtotal;
    }

    public void printShoppingCart() {
        System.out.println("LIST OF PRODUCT------");
        int i = 0;
        for (OrderDetailItemDTO p : productsSC) {
            System.out.println(i + "--" + p);
            i++;
        }
        System.out.println("END ---LIST OF PRODUCT");
    }

    public int findShoppingCartProduct(ProductDTO product) {
        int i = 0;
        for (OrderDetailItemDTO scp : productsSC) {
            if (scp.getProduct().getId() == product.getId()) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public String seeBasket() {

        return "SeeBasket.xhtml";
    }

    public String logout() {
        storeId = 0;
        user = null;
        productsSC = new ArrayList<>();
        whereWasCalled = null;
        return "index.xhtml";
    }
}
