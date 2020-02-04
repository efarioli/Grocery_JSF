/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dbase.UserGateway;
import dto.ProductDTO;
import dto.StoreDTO;
import dto.UserDTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import ui_admin.CommandFactory;

/**
 *
 * @author f023507i
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean implements Serializable {

    @Inject
    ShoppingCart shoppingCart;

    /**
     * Creates a new instance of UserBean
     */
    private int id;
    private String name;
    private String username;
    private String password;
    private String confirmPass;
    private String address;
    private boolean credentialsOK;

    public UserBean() {
    }

    public UserBean(int id, String name, String uname, String password, String address) {
        this.id = id;
        this.name = name;
        this.username = uname;
        this.password = password;
        this.address = address;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String addUser()  {
        UserDTO u = new UserDTO(0, getName(), getUsername(), getPassword(), getAddress());
        UserGateway ug = new UserGateway();
        //set the userID from the insertion in the DB - if user is not inserted return -1
        u.setId(ug.insertUser(u));

        shoppingCart.setUser(u);
        //Redirect to the previous page or Index
            return shoppingCart.getPreviousURI() + "?faces-redirect=true";
    }

    public String checkCredentials() {
        credentialsOK = false;
        UserGateway ug = new UserGateway();

        UserDTO u = ug.checkCredentials(username, password);
        if (u != null) {
            credentialsOK = true;
            shoppingCart.setUser(u);
        }

        if (credentialsOK) {
            return shoppingCart.getPreviousURI() + "?faces-redirect=true";


        } else {
            clearCredentials();
            System.out.println("***" + FacesContext.getCurrentInstance().getAttributes().toString());
            FacesContext.getCurrentInstance().addMessage("login:buttoncheck", new FacesMessage("Login credentials are not correct"));
            //first param is the id where the messega will be displayed
            return null;
        }

    }

    private void clearCredentials() {
        this.name = "";
        this.username = "";
        this.password = "";
        this.id = 0;
        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

//    private String getRedirect(String endURL) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        ExternalContext extContext = context.getExternalContext();
//        String referrer = extContext.getRequestHeaderMap().get("referer");
//
//        //referrer = LOcalHos...buy.xhtml
//        String ref = referrer.split("/")[referrer.split("/").length - 1];
//        System.out.println("///////////////////////////////////////////////////////////////////////");
//        System.out.println("End URl " + endURL);
//        System.out.println("REF URL =" + referrer + "---" + ref);
//        System.out.println("///////////////////////////////////////////////////////////////////////");
//        if (ref.equals(endURL) || referrer.contains("sessionid")) {
//            //the session id part is just if the user open the app and the first action performed is the login
//
//            ref = "index.xhtml";
//        }
//        return ref;
//
//    }

}
