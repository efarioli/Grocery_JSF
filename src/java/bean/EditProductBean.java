/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dto.ProductDTO;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import manager.ProductManager;
import ui_admin.CommandFactory;

/**
 *
 * @author ezequ
 */
@Named(value = "editProductBean")
@RequestScoped
public class EditProductBean
{
    private ProductDTO product = new ProductDTO(0, "", "", 0);
    

    public EditProductBean()
    {
    }

    public ProductDTO getProduct()
    {
        return product;
    }

    public void setProduct(ProductDTO product)
    {
        this.product = product;
    }

    public void findProduct(int id)
    {
         new CommandFactory()
                .createCommand(CommandFactory.FIND_PRODUCT, id)
                .execute();
        ProductManager pMngr = new ProductManager();
        ProductDTO pr = pMngr.findProduct(id);
        setProduct(pr);
    }

    public String updateProduct()
    {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
//        System.out.println(productID);
//        getProduct().setId(productID);
        System.out.println(product);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        new CommandFactory()
                .createCommand(CommandFactory.UPDATE_PRODUCT, product)
                .execute();
        return "/admin/ViewAllProducts.xhtml?faces-redirect=true";
    }
}
