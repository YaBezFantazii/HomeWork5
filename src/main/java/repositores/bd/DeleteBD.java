package repositores.bd;

import java.sql.*;

public class DeleteBD {

    // Метод, которые очищает базу данных (удаляет старые таблицы и создает новые)
    public static void DeleteBD () {
        try {
            Connection con = ConnectBD.ConnectDB();
            DatabaseMetaData meta = con.getMetaData();
            Statement statement = con.createStatement();

            ResultSet resultSet = meta.getTables(null, null, "RATING", null);
            if (resultSet.next()) {
                statement.executeUpdate("DROP TABLE Rating");
            }

            resultSet = meta.getTables(null, null, "GAME", null);
            if (resultSet.next()) {
                statement.executeUpdate("DROP TABLE Game");
            }
            CheckNewTableBD.CheckNewTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
