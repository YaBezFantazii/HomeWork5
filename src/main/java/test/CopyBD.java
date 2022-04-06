package test;

import repositores.bd.CheckNewTableBD;
import repositores.bd.ConnectBD;
import repositores.bd.FormatStepBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

// (тесты) Вспомогательный класс для тестов БД.
public class CopyBD {

    // (тесты) Вспомогательный метод для тестов БД. Возращает данные из основной бд, удаляет таблицы,
    // создает временые, заполняет данными.
    public static ArrayList<ResultSet> CopyTimeBD () throws SQLException {

            ArrayList<Integer> a = new ArrayList<>();
            Connection con = ConnectBD.ConnectDB();
            Statement statement = con.createStatement();
            PreparedStatement query;
            ArrayList<ResultSet> res = new ArrayList<>();

            CheckNewTableBD.CheckNewTable();

            query = con.prepareStatement("select * from game");
            ResultSet result = query.executeQuery();
            res.add(result);
            query = con.prepareStatement("select * from rating");
            result = query.executeQuery();
            res.add(result);

            statement.executeUpdate("DROP TABLE Rating");
            statement.executeUpdate("DROP TABLE Game");

            CheckNewTableBD.CheckNewTable();

            query = con.prepareStatement("Insert into rating (player,win,lose,draw) values" +
                    "('test1',2,0,5)");
            query.execute();
            query = con.prepareStatement("Insert into rating (player,win,lose,draw) values" +
                    "('test2',1,4,3)");
            query.execute();

            query = con.prepareStatement("insert into Game (player1,player2,steps,result)" +
                    "values('test1','test2',?,0)");
            a.addAll(Arrays.asList(1,2,3,4,5,6,7));
            query.setString(1, FormatStepBD.FormatStepBD(a));
            query.execute();

            query = con.prepareStatement("insert into Game (player1,player2,steps,result)" +
                    "values('max','тест4',?,2)");
            a.addAll(Arrays.asList(1,2,3,5,8,9,6,4,7));
            query.setString(1, FormatStepBD.FormatStepBD(a));
            query.execute();

            return res;
    }

    //(тесты) Вспомогательный метод для тестов БД.
    // Удаляет временные таблицы, создает новые, заполняет их сохраненными данными
    public static void DeleteTimeBD (ArrayList<ResultSet> res) throws SQLException {
        // (только для тестов) После тестов удаляем бд и возвращаем из копии старую
        Connection con = ConnectBD.ConnectDB();
        Statement statement = con.createStatement();
        PreparedStatement query;

        statement.executeUpdate("DROP TABLE Rating");
        statement.executeUpdate("DROP TABLE Game");

        CheckNewTableBD.CheckNewTable();

        while (res.get(0).next()) {
            query = con.prepareStatement("insert into Game (player1,player2,steps,result)" +
                    "values(?,?,?,?)");
            query.setString(1, res.get(0).getString(2));
            query.setString(2, res.get(0).getString(3));
            query.setString(3, res.get(0).getString(4));
            query.setInt(4, res.get(0).getInt(5));
            query.execute();
        }

        while (res.get(1).next()) {
            query = con.prepareStatement("Insert into rating (player,win,lose,draw) values" +
                    "(?,?,?,?)");
            query.setString(1, res.get(1).getString(1));
            query.setInt(2, res.get(1).getInt(2));
            query.setInt(3, res.get(1).getInt(3));
            query.setInt(4, res.get(1).getInt(4));
            query.execute();
        }

        con.close();
    }
}
