package dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author zekeFarioli
 */
public class ProductDTO implements Serializable
{
    private  int id;
    private  String description;
    private  String imgURL;
    private  float price;

    public ProductDTO(int id, String description, String imgURL, float price)
    {
        this.id = id;
        this.description = description;
        this.imgURL = imgURL;
        this.price = price;
    }

    public int getId()
    {
        return id;
    }

    public String getDescription()
    {
        return description;
    }

    public String getImgURL()
    {
        return imgURL;
    }       

    public float getPrice()
    {
        return price;
    }

    @Override
    public String toString()
    {
        return "ProductDTO{" + "id=" + id + ", description=" + description + ", imgURL=" + imgURL + ", price=" + price + '}';
    }

    public void setId(int id)
    {
        System.out.println("SETTING ID TO: " + id);
        this.id = id;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setImgURL(String imgURL)
    {
        this.imgURL = imgURL;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }
    
    public String trimString (String str, int max){
        return str.substring(0, Math.min(20,str.length()));
    }
    
    
    
}
