/**
 *
 * @author admin
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    private static DBContext instance = new DBContext();
    private Connection connection;

    private DBContext() {
        try {
            if (connection == null || connection.isClosed()) {
                String user = "sa";
                String password = "123";
                String url = "jdbc:sqlserver://localhost:1433;databaseName=PRJDB";
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(url, user, password);
            }
        } catch (Exception e) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
            connection = null;
        }
    }

    public static DBContext getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
