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
public class AdminUserDTO implements Serializable
{
    private final int id;
    private final String name;
    private final String uname;
    private final String password;
    private final String address;
    private final int storeId;

    public AdminUserDTO(int id, String name, String uname, String password, String address, int storeId)
    {
        this.id = id;
        this.name = name;
        this.uname = uname;
        this.password = password;
        this.address = address;
        this.storeId = storeId;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getUname()
    {
        return uname;
    }

    public String getPassword()
    {
        return password;
    }

    public String getAddress()
    {
        return address;
    }

    public int getStoreId()
    {
        return storeId;
    }
    
}
