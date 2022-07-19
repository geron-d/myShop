package by.it.academy.contants;

public class HQL {

    public static final String GET_CATEGORY_BY_NAME_HQL = "FROM Category C WHERE C.category = :category";
    public static final String GET_ALL_CATEGORIES_HQL = "FROM Category C";
    public static final String GET_ALL_CATEGORIES_DESC_HQL = "FROM Category C order by id desc";

    public static final String GET_TYPE_BY_NAME_HQL = "FROM Type T WHERE T.type = :type";
    public static final String GET_ALL_TYPES_HQL = "FROM Type T";
    public static final String GET_ALL_TYPES_DESC_HQL = "FROM Type T order by id desc";

    public static final String GET_PRODUCER_BY_NAME_HQL = "FROM Producer P WHERE P.producer = :producer";
    public static final String GET_ALL_PRODUCERS_HQL = "FROM Producer P";
    public static final String GET_ALL_PRODUCERS_DESC_HQL = "FROM Producer P order by id desc";

    public static final String GET_PRODUCT_BY_CATEGORY_TYPE_PRODUCER_NAME = "FROM Product P WHERE " +
            "P.category = :category AND P.type = :type AND P.name = :name AND P.producer = :producer";
    public static final String GET_ALL_PRODUCTS_HQL = "FROM Product P";
    public static final String GET_ALL_PRODUCTS_DESC_HQL = "FROM Product P order by id desc";
    public static final String GET_PRODUCTS_BY_CATEGORY_HQL = "FROM Product P WHERE P.category = :category order by id";
    public static final String GET_PRODUCTS_BY_CATEGORY_DESC_HQL = "FROM Product P WHERE P.category = :category " +
            "order by id desc";
    public static final String PRODUCTS_SEARCH_HQL = "FROM Product P WHERE lower(P.name) like lower(:search) " +
            "or lower(P.category.category) like lower(:search) " +
            "or lower(P.type.type) like lower(:search) " +
            "or lower(P.producer.producer) like lower(:search) " +
            "order by id desc";
    public static final String GET_PRODUCTS_BY_TYPE_HQL = "FROM Product P WHERE P.type = :type order by id";
    public static final String GET_PRODUCTS_BY_TYPE_DESC_HQL = "FROM Product P WHERE P.type = :type order by id desc";

    public static final String GET_ALL_USERS_HQL = "FROM User U";
    public static final String GET_ALL_USERS_DESC_HQL = "FROM User U order by id desc";
    public static final String GET_USER_BY_LOGIN_PASSWORD_HQL = "FROM User U " +
            "WHERE U.login = :login AND U.password = :password";
    public static final String GET_USER_BY_LOGIN_HQL = "FROM User U WHERE U.login = :login";

    public static final String GET_ALL_PRODUCTS_IN_BUCKET_HQL = "FROM product_in_bucket PIB";
    public static final String GET_ALL_PRODUCTS_IN_BUCKET_DESC_HQL = "FROM product_in_bucket PIB order by id desc";
    public static final String GET_PRODUCTS_IN_BUCKET_BY_USER_PRODUCT_HQL = "FROM product_in_bucket PIB " +
            "WHERE PIB.user = :user AND PIB.product = :product";
    public static final String GET_PRODUCTS_IN_BUCKET_BY_USER_HQL = "FROM product_in_bucket PIB WHERE PIB.user = :user";


}
