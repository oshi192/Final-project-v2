package util;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleManager {
    public static final String TAXI_ALL_JOIN = "taxi-all-join";
    public static final String TAXI_ALL = "taxi-all";
    public static final String TAXI_COUNT = "taxi-count";
    public static final String TAXI_BY_STATUS = "taxi-by-status";
    public static final String TAXI_CREATE = "taxi-create";
    public static final String TAXI_BY_ID = "taxi-by-id";

    public static final String CARTYPE_ALL = "cartype-all";
    public static final String CARTYPE_BY_CARTYPENAME = "cartype-by-cartypename";
    public static final String CARTYPE_COUNT = "cartype-count";
    public static final String CARTYPE_CREATE = "cartype-create";
    public static final String CARTYPE_DELETE = "cartype-delete";
    public static final String CARTYPE_BY_ID = "cartype-by-id";

    public static final String USER_COUNT = "user-count";
    public static final String USER_ALL = "user-all";

    public static final String ORDER_COUNT = "order-count";
    public static final String ORDER_CREATE = "order-create";
    public static final String ORDER_ALL_BY_USER_ID = "order-all-by-userid";
    public static final String ORDER_ALL_BY_USER_ID_PAGGINATE = "order-all-by-userid-paginate";
    public static final String ORDER_ALL_BY_USER_ID_PAGGINATE_JOIN = "order-all-by-userid-paginate-join";


    public static final String DISCOUNT_COUNT = "discount-count";
    public static final String DISCOUNT_DELETE = "discount-delete";
    public static final String DISCOUNT_CREATE = "discount-create";
    public static final String DISCOUNT_ALL = "discounts-all";
    public static final String DISCOUNT_BY_TIME = "discount-by-date";
    public static final String DISCOUNT_ERROR_START_TIME = "check-statrtime";
    public static final String DISCOUNT_ERROR_END_TIME = "check-endTime";
    public static final String DISCOUNT_ERROR_TEXT = "check-text";
    public static final String DISCOUNT_ERROR_TEXT_UK = "check-text_uk";

    public static final String CITY_ALL = "city-all";

    public static final String CITYDISTANCE_BY_IDS = "citydistance-by-ids";
    public static final String MSG_ORDER_CONFIRM = "msg-confirm";


    private static final String BUNDLE_NAME_MESSAGE = "message";
    private static final String BUNDLE_NAME_CONFIG = "";
    private static final String BUNDLE_NAME_SQL = "sql";
    private static final String BUNDLE_NAME_PATH = "paths";

    public static final String PAGE_INDEX_PATH = "PAGE_INDEX_PATH";
    public static final String PAGE_LOGIN_PATH = "PAGE_LOGIN_PATH";
    public static final String PAGE_REGISTER_PATH = "PAGE_REGISTER_PATH";
    public static final String PAGE_TAXIS_PATH = "PAGE_TAXIS_PATH";
    public static final String PAGE_USER_HISTORY_PATH = "PAGE_USER_HISTORY_PATH";
    public static final String PAGE_CARTYPES = "PAGE_CARTYPES";
    public static final String PAGE_ORDER_A_TAXI = "PAGE_ORDER_A_TAXI";
    public static final String PAGE_DISCOUNT = "PAGE_DISCOUNT";
    public static final String PAGE_TAKE_ORDER = "PAGE_TAKE_ORDER";


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
