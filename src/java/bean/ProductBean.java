package bean;

import dto.ProductDTO;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import ui_admin.CommandFactory;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author zekeFarioli
 */
@Named(value = "productBean")
@RequestScoped
public class ProductBean implements Serializable
{

    @Inject
    EditProductBean editProductBean;

    private ProductDTO product = null;
    private int productQty = 0;

    public int getProductQty()
    {
        return productQty;
    }

    public void setProductQty(int productQty)
    {
        this.productQty = productQty;
    }
    private ArrayList<ProductDTO> products = new ArrayList<>();
    private String title = "";

    private int id;
    private String description;
    private String imgURL;
    private float price;

    public ProductBean()
    {
    }

    public void fetchProductDetails(int prodID)
    {
        product
                = (ProductDTO) CommandFactory
                        .createCommand(
                                CommandFactory.FIND_PRODUCT,
                                prodID)
                        .execute();

        //return "viewCustomer";
    }

    public ArrayList<ProductDTO> getProducts()
    {
        products = (ArrayList<ProductDTO>) new CommandFactory()
                .createCommand(CommandFactory.VIEW_ALL_PRODUCTS)
                .execute();
        return products;
    }

    public void setProductDetails(ProductDTO productDetails)
    {
        this.product = productDetails;
    }

    public String addProduct()
    {
        ProductDTO pr = new ProductDTO(0, getDescription(), getImgURL(), getPrice());
        new CommandFactory()
                .createCommand(CommandFactory.ADD_PRODUCT, pr)
                .execute();
        title = "";

        return "/admin/ViewAllProducts.xhtml?faces-redirect=true";
    }

    public String editProduct(ProductDTO product)
    {
     
        editProductBean.setProduct(product);

        return "/admin/editProduct.xhtml";
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getImgURL()
    {
        return imgURL;
    }

    public void setImgURL(String imgURL)
    {
        this.imgURL = imgURL;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

}
