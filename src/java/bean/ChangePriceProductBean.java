//
//package bean;
//
//import dto.Product;
//import product_ui.CommandFactory;
//import java.util.Date;
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//
///**
// *
// * @author ezequ
// */
//@Named(value = "changePriceProduct")
//@RequestScoped
//public class ChangePriceProductBean
//{
//
//    @Inject
//    ProductBean productBean;
//
//    private int id;
//    private String description;
//    private String imgURL;
//    private double price;
//
//    public ChangePriceProductBean()
//    {
//    }
//
//    public String changeProceProduct(Product pr)
//    {
//        Product newProduct
//                = new Product(
//                        id,
//                        description,
//                        imgURL,
//                        price
//                );
//
//        Product insertedProduct
//                = (Product) CommandFactory
//                        .createCommand(CommandFactory.ADD_PRODUCT,
//                                newProduct)
//                        .execute();
//
//   //     productBean.setProductDetails(insertedProduct);
//
//        return "viewProduct";
//    }
//
//}
//
