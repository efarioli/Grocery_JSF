package ui_admin;

import dto.ProductDTO;

/**
 *
 * @author gdm1
 */
public class CommandFactory
{

    public static final int VIEW_ALL_PRODUCTS = 1;
    public static final int ADD_PRODUCT = 2;
    public static final int EDIT_PRODUCT = 3;
    public static final int UPDATE_PRODUCT = 4;
    public static final int FIND_PRODUCT = 5;
    public static final int DELETE_PRODUCTS_IN_STORES = 6;
    public static final int UPDATE_QTY_IN_STORE = 7;

    public static Command createCommand(int code)
    {
        switch (code)
        {
            case VIEW_ALL_PRODUCTS:
                return new ViewAllProductCommand();
            default:
                return null;
        }
    }

    public static Command createCommand(int code, ProductDTO product)
    {
        switch (code)
        {
            case ADD_PRODUCT:
                return new AddProductCommand(product);
//            case CHANGE_PRICE_PRODUCT:
//                return new ChangePriceProductCommand(product);
            case EDIT_PRODUCT:
                return new EditProductCommand(product);
            case UPDATE_PRODUCT:
                return new UpdateProductCommand(product);
            case DELETE_PRODUCTS_IN_STORES:
                return new DeleteProductsInStoresCommand(product);            
            default:
                return null;

        }
    }

    public static Command createCommand(int code, int productID)
    {
        switch (code)
        {
            case FIND_PRODUCT:
                System.out.println("===============================================");
//                System.out.println((ProductDTO) (new FindProductCommand(productID)));
                System.out.println("===============================================");
                return new FindProductCommand(productID);
            default:
                return null;
        }
    }

    public static Command createCommand(int code, int storeID, int productID, int qty)
    {
        switch (code)
        {
            case UPDATE_QTY_IN_STORE:
                return new UpdateQtyInStoreCommand(storeID, productID, qty);                
            default:
                return null;
        }

    }

}
