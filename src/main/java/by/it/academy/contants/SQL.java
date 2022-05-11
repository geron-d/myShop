package by.it.academy.contants;

public class SQL {
    public static final String USER_INSERT_SQL = "INSERT INTO users (login, password, accessLevel) VALUES (?,?,?)";
    public static final String USER_GET_SQL = "SELECT * FROM users WHERE id = ?";
    public static final String USER_UPDATE_SQL = "UPDATE users SET login=?, password=?, accessLevel=? WHERE id=?";
    public static final String USER_DELETE_SQL = "DELETE FROM users WHERE id=?";
    public static final String USER_GET_ALL_USERS_SQL = "SELECT * FROM users";
    public static final String USER_GET_BY_LOGIN_PASSWORD_SQL = "SELECT * FROM users WHERE login = ? AND password = ?";
    public static final String USER_CHECK_LOGIN_SQL = "SELECT * FROM users WHERE login = ?";

    public static final String PRODUCT_INSERT_SQL = "INSERT INTO products (category, type, name, image, date, " +
            "producer, amount, price) VALUES (?,?,?,?,?,?,?,?)";
    public static final String PRODUCT_GET_SQL = "SELECT * FROM products WHERE id = ?";
    public static final String PRODUCT_UPDATE_SQL = "UPDATE products SET category=?, type=?, name=?, image=?, " +
            "date=?, producer=?, amount=?, price=? WHERE id=?";
    public static final String PRODUCT_DELETE_SQL = "DELETE FROM products WHERE id=?";
    public static final String PRODUCT_GET_ALL_PRODUCTS_SQL = "SELECT * FROM products ORDER BY id ";
    public static final String PRODUCT_GET_PRODUCTS_IN_CATEGORY_SQL = "SELECT * FROM products WHERE category=? ORDER BY id ";
    public static final String PRODUCT_GET_PRODUCTS_IN_TYPE_SQL = "SELECT * FROM products WHERE type=? ORDER BY id ";


    public static final String LIMIT_SQL = " LIMIT ?";
}
