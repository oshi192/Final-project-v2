package util;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleManager {
    private static final String BUNDLE_NAME_MESSAGE = "message";
    private static final String BUNDLE_NAME_CONFIG = "";
    private static final String BUNDLE_NAME_SQL = "sql";
    private static final String BUNDLE_NAME_PATH = "paths";

    public static final String PAGE_INDEX_PATH = "PAGE_INDEX_PATH";
    public static final String PAGE_LOGIN_PATH = "PAGE_LOGIN_PATH";
    public static final String PAGE_REGISTER_PATH = "PAGE_REGISTER_PATH";

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
