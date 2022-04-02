package DataBase;

import java.sql.*;

public class CheckNewTable {

    public static void CheckNewTable () {
        try {
            Connection con = ConnectDB.ConnectDB();
            DatabaseMetaData meta = con.getMetaData();
            Statement statement = con.createStatement();

            ResultSet resultSet = meta.getTables(null, null, "RATING", null);
            if (!resultSet.next()) {
                Statement createStatement = con.createStatement();
                createStatement.executeUpdate("CREATE TABLE Rating ( " +
                        "   player VARCHAR(30) PRIMARY KEY, " +
                        "   win INT NOT NULL, " +
                        "   lose INT NOT NULL, " +
                        "   draw INT NOT NULL );");
            }

            resultSet = meta.getTables(null, null, "GAME", null);
            if (!resultSet.next()) {
                Statement createStatement = con.createStatement();
                createStatement.executeUpdate("CREATE TABLE Game (" +
                        "                        id IDENTITY," +
                        "                        player1 VARCHAR(30) NOT NULL ," +
                        "                        player2 VARCHAR(30) NOT NULL ," +
                        "                        steps VARCHAR(30) NOT NULL," +
                        "                        result INT NOT NULL);");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
