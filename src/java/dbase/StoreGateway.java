/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbase;

import dto.ProductDTO;
import dto.StoreDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author f023507i
 */
public class StoreGateway extends DB_Manager
{
     public ArrayList<StoreDTO> getAllStores()
    {
        ArrayList<StoreDTO> stores = new ArrayList<>();

        try
        {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM STORES");

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                stores.add(new StoreDTO(
                        rs.getInt("STORE_ID"),
                        rs.getString("STORE_NAME"),
                        rs.getString("STORE_ADDRESS")
                ));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return new ArrayList<StoreDTO>();
        }

        return stores;
    }
    
}
