package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by user on 29.09.16.
 */
public class DbHelper {
    private Connection connection;
    private String DRIVER = "org.sqlite.JDBC";
    private String URL = "jdbc:sqlite:database.db";
    private static DbHelper ourInstance = new DbHelper();

    public static DbHelper getInstance() {
        return ourInstance;
    }

    private DbHelper() {
        try {
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL);
            createTables();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection()
    {
        return connection;
    }

    private void createTables()
    {
        try {
            Statement st = connection.createStatement();
            InputStream is = getClass().getResourceAsStream("createbase/database.sql");
            StringBuilder sb = new StringBuilder();
            while (is.available() > 0)
            {
                char x = (char) is.read();
                sb.append(x);
                if(x == ';')
                {
                    st.execute(String.valueOf(sb));
                    sb = new StringBuilder();
                }
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
