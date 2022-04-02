package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;


    public class ConnectDB {

        public static Connection ConnectDB() {

            Connection con = null;
            try {
                Class.forName("org.h2.Driver");
                con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return con;

        }

    }

