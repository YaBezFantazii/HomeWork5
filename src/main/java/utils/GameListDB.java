package utils;

import repositores.bd.ConnectBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameListDB {

    // Вспомогательный статический метод, для вывода списка игр из Базы Данных
    public static ArrayList<String> GameListDB() throws SQLException {

        ArrayList<String> result= new ArrayList<>();
        Connection con = ConnectBD.ConnectDB();
        PreparedStatement query;

        query = con.prepareStatement("select * from game;");
        ResultSet res = query.executeQuery();
        while (res.next()){
            result.add(res.getString(1)+") "+res.getString(2)+"-"+res.getString(3));
        }
        return result;
    }
}
