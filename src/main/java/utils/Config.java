package utils;

public class Config {

    /* client */
    public static final String INDEX_CLIENT_LINK = "/jsp/index.jsp";
    public static final String SEARCH_LINK = "/jsp/view/client/jsp/search.jsp";
    public static final String PRODUCT_CLIENT_LIST_LINK = "/view/client/view/product-list.jsp";
    public static final String PRODUCT_CLIENT_DETAIL_LINK = "/jsp/view/client/jsp/product-detail.jsp";
    public static final String ORDER_CLIENT_DETAIL_LINK = "/jsp/view/client/jsp/order-detail.jsp";
    public static final String MY_ACCOUNT_LINK = "/jsp/view/client/jsp/myaccount.jsp";
    public static final String LOGIN_LINK = "/jsp/view/client/jsp/login.jsp";
    public static final String NOTIFICATION_LINK = "/jsp/view/client/jsp/notification.jsp";
    public static final String YOUR_CART_LINK = "/jsp/view/client/jsp/your-cart.jsp";
    public static final String FORGOT_PASSWORD_LINK = "/jsp/view/client/jsp/forgot-password.jsp";

    /* admin */
    public static final String INDEX_ADMIN_LINK = "/jsp/view/admin/jsp/index.jsp";
    public static final String LIST_USER_LINK = "/jsp/view/admin/jsp/list-user.jsp";
    public static final String ADD_USER_LINK = "/jsp/view/admin/jsp/add-user.jsp";
    public static final String LIST_PRODUCT_LINK = "/jsp/view/admin/jsp/list-product.jsp";
    public static final String EDIT_PRODUCT_LINK = "/jsp/view/admin/jsp/edit-product.jsp";
    public static final String ADD_PRODUCT_LINK = "/jsp/view/admin/jsp/add-product.jsp";
    public static final String LIST_ORDER_LINK = "/jsp/view/admin/jsp/list-order.jsp";
    public static final String EDIT_ORDER_LINK = "/jsp/view/admin/jsp/edit-order.jsp";

    /* regex */
    public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PHONE_REGEX = "^[0-9]{10}$";

    /* save location */
    public static final String SAVE_BILL_LOCATION = "/home/quan/DataForProject/demo-hibernate-and-servlet/Bill/";
    public static final String SAVE_PRODUCT_LOCATION = "/home/quan/DataForProject/demo-hibernate-and-servlet/Product/";
    public static final String SAVE_USER_LOCATION = "/home/quan/DataForProject/demo-hibernate-and-servlet/User/";
}