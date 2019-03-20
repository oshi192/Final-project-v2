package util;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleManager {
    public static final String TAXI_ALL_JOIN = "taxi-all-join";
    public static final String TAXI_ALL = "taxi-all";
    public static final String TAXI_COUNT = "taxi-count";

    public static final String CARTYPE_ALL = "cartype-all";
    public static final String CARTYPE_BY_CARTYPENAME = "cartype-by-cartypename";
    public static final String CARTYPE_COUNT = "cartype-count";

    public static final String USER_COUNT = "user-count";
    public static final String USER_ALL = "user-all";

    public static final String ORDER_COUNT = "order-count";
    public static final String ORDER_ALL_BY_USER_ID = "order-all-by-userid";
    public static final String ORDER_ALL_BY_USER_ID_PAGGINATE = "order-all-by-userid-paginate";

    public static final String DISCOUNT_COUNT = "discount-count";

    private static final String BUNDLE_NAME_MESSAGE = "message";
    private static final String BUNDLE_NAME_CONFIG = "";
    private static final String BUNDLE_NAME_SQL = "sql";
    private static final String BUNDLE_NAME_PATH = "paths";

    public static final String PAGE_INDEX_PATH = "PAGE_INDEX_PATH";
    public static final String PAGE_LOGIN_PATH = "PAGE_LOGIN_PATH";
    public static final String PAGE_REGISTER_PATH = "PAGE_REGISTER_PATH";
    public static final String PAGE_TAXIS_PATH = "PAGE_TAXIS_PATH";
    public static final String PAGE_USER_HISTORY_PATH = "PAGE_USER_HISTORY_PATH";


    public static final String USER_BY_ID = "user-findById";

    public static void setNewLocale(Locale locale) {
        Locale.setDefault(locale);
    }

    public static String getMessage(String key) {
        return ResourceBundle.getBundle(BUNDLE_NAME_MESSAGE, new UTF8Control()).getString(key);
    }
    public static String getConfig(String key) {
        return ResourceBundle.getBundle(BUNDLE_NAME_CONFIG, new UTF8Control()).getString(key);
    }
    public static String getSqlString(String key) {
        return ResourceBundle.getBundle(BUNDLE_NAME_SQL, new UTF8Control()).getString(key);
    }
    public static String getPath(String key) {
        return ResourceBundle.getBundle(BUNDLE_NAME_PATH).getString(key);
    }
}
