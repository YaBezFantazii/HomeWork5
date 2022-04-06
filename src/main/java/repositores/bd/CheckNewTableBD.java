package repositores.bd;

import java.sql.*;

public class CheckNewTableBD {

    public static void CheckNewTable () {
        // Проверка есть ли необходимые для работы приложения таблицы в бд, если нет, то создаем их.
        try {
            Connection con = ConnectBD.ConnectDB();
            DatabaseMetaData meta = con.getMetaData();
            Statement statement = con.createStatement();

            ResultSet resultSet = meta.getTables(null, null, "RATING", null);
            if (!resultSet.next()) {
                statement.executeUpdate("CREATE TABLE Rating ( " +
                        "   player VARCHAR(30) PRIMARY KEY, " +
                        "   win INT NOT NULL, " +
                        "   lose INT NOT NULL, " +
                        "   draw INT NOT NULL );");
                //System.out.println("Таблица Rating создана");
            }

            resultSet = meta.getTables(null, null, "GAME", null);
            if (!resultSet.next()) {
                statement.executeUpdate("CREATE TABLE Game (" +
                        "                        id IDENTITY," +
                        "                        player1 VARCHAR(30) NOT NULL ," +
                        "                        player2 VARCHAR(30) NOT NULL ," +
                        "                        steps VARCHAR(30) NOT NULL," +
                        "                        result INT NOT NULL);");
                //System.out.println("Таблица Game создана");
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
