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
public class UserDTO implements Serializable
{
    private  int id;
    private  String name;
    private  String uname;
    private  String password;
    private  String address;

    public UserDTO(int id, String name, String uname, String password, String address)
    {
        this.id = id;
        this.name = name;
        this.uname = uname;
        this.password = password;
        this.address = address;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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
        return "UserDTO{" + "id=" + id + ", name=" + name + ", uname=" + uname + ", password=" + password + ", address=" + address + '}';
    }
    
}
