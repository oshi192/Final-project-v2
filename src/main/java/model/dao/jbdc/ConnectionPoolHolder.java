package model.dao.jbdc;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.util.ResourceBundle;

public class ConnectionPoolHolder {
    private static BasicDataSource dataSource;


    public static DataSource getDataSource() {

        ResourceBundle bundle = ResourceBundle.getBundle("db_config");
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    dataSource = new BasicDataSource();
                    dataSource.setDriverClassName(bundle.getString("db.driver.class"));
                    dataSource.setUrl(bundle.getString("db.url"));
                    dataSource.setUsername(bundle.getString("db.user"));
                    dataSource.setPassword(bundle.getString("db.password"));
                    dataSource.setMaxOpenPreparedStatements(100);
                }
            }
        }
        return dataSource;
    }
}