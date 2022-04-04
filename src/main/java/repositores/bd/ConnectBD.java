package repositores.bd;

import java.sql.Connection;
import java.sql.DriverManager;


    public class ConnectBD {

        // Метод для подключения к субд "h2" через jdbs
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

