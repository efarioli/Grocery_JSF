/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import dto.StoreDTO;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author f023507i
 */
@Named(value = "adminBean")
@SessionScoped
public class AdminUserBean implements Serializable
{

//    @Inject
//    StockBean stockBean;
    @Inject
    ProductBean productBean;

    /**
     * Creates a new instance of AdminBean
     */
    private int adminID;
    private String name;
    private String uname;
    private String password;
    private String address;
    private boolean credentialsOK = false;

    private StoreDTO st;

//    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    public AdminUserBean()
    {
    }

    public boolean credentialsAreOK()
    {

        return credentialsOK;
    }

    public StoreDTO getSt()
    {
        return st;
    }

    public void setSt(StoreDTO st)
    {
        this.st = st;
    }

    public String logout()
    {
        clearCredentials();
        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/admin/index.xhtml?faces-redirect=true";
    }

    public String checkCredentials()
    {
        credentialsOK = false;
        try
        {

            DriverManager.registerDriver(
                    new org.apache.derby.jdbc.ClientDriver());
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/grocery", "emp", "emp");
            //Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/grocery22", "emp", "emp");

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ADMIN_Users JOIN STORES  ON STORES.STORE_ID = ADMIN_USERS.STORE_ID WHERE U_Name = ?");
            stmt.setString(1, uname);
            ResultSet rs = stmt.executeQuery();
            
            String password1 = password;
            byte[] hash
                    = MessageDigest.getInstance("SHA-256")
                            .digest(password1.getBytes(StandardCharsets.UTF_8));
            password1 = Base64.getEncoder().encodeToString(hash);
            System.out.println("PASSWORDDDD");
            System.out.println(password1);
            System.out.println("PASSWORDDDD");
      

            if (rs.next() && rs.getString("password").equals(password1))
            {
                credentialsOK = true;
                StoreDTO store = new StoreDTO(rs.getInt("STORE_ID"), rs.getString("STORE_NAME"), rs.getString("STORE_ADDRESS"));
                setSt(store);
                name = rs.getString("NAME");
                adminID = rs.getInt("ADMIN_ID");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e)
        {
            Logger.getLogger(AdminUserBean.class.getName()).log(Level.SEVERE, e.toString());
        }

        if (credentialsOK)
        {
//            StoreDTO st = new StoreDTO(1, "sansbury", "adrresss oof sansbury");
//            sessionMap.put("objStoreForAdmin", st);

            return "/admin/ViewAllProducts.xhtml?faces-redirect=true";
        } else
        {
            clearCredentials();
            FacesContext.getCurrentInstance().addMessage("login:buttoncheck", new FacesMessage("Login credentials are not correct. Please try again"));
            return null;
        }

    }

    public void clearCredentials()
    {
        this.name = "";
        this.uname = null;
        this.password = "";
        this.adminID = 0;
        this.st = null;
        this.credentialsOK = false;

        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

    }

    public AdminUserBean(int id, String name, String uname, String address)
    {
        this.adminID = id;
        this.name = name;
        this.uname = uname;
        this.address = address;
        this.credentialsOK = false;
    }

    public int getAdminID()
    {
        return adminID;
    }

    public void setAdminID(int adminID)
    {
        this.adminID = adminID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUname()
    {
        return uname;
    }

    public void setUname(String uname)
    {
        this.uname = uname;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Override
    public String toString()
    {
        return "AdminUserBean{" + "productBean=" + productBean + ", adminID=" + adminID + ", name=" + name + ", uname=" + uname + ", password=" + password + ", address=" + address + ", credentialsOK=" + credentialsOK + ", st=" + st + '}';
    }

    

   

}
