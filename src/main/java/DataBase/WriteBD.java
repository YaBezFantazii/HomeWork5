package DataBase;

import Class.GameList;
import HelpMethods.WriteRead;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WriteBD implements WriteRead.Write {

    public String Write (GameList GameList) {
        try {
            Connection con = ConnectDB.ConnectDB();
            PreparedStatement query;

            if (GameList.getWin() == 0) {
                rating(GameList.getNickName1(), 1, 0, 0);
                rating(GameList.getNickName2(), 0, 1, 0);
            } else if (GameList.getWin() == 1) {
                rating(GameList.getNickName1(), 0, 1, 0);
                rating(GameList.getNickName2(), 1, 0, 0);
            } else {
                rating(GameList.getNickName1(), 0, 0, 1);
                rating(GameList.getNickName2(), 0, 0, 1);
            }

            query = con.prepareStatement("insert into Game (player1,player2,steps,result)" +
                    "values(?,?,?,?)");
            query.setString(1, GameList.getNickName1());
            query.setString(2, GameList.getNickName2());
            query.setString(3, FormatStepBD.FormatStepBD(GameList.getCell()));
            query.setInt(4, GameList.getWin());
            query.execute();
        } catch (SQLException e){
            System.out.println(e);
        }
        return "";
    }


    private static void rating (String nickname, int win, int lose, int draw) throws SQLException {
        Connection con = ConnectDB.ConnectDB();
        PreparedStatement query = con.prepareStatement("select * from rating where player=?");
        query.setString(1,nickname);
        ResultSet res = query.executeQuery();
        if (res.next()) {
            win += res.getInt(2);
            lose += res.getInt(3);
            draw += res.getInt(4);
            query = con.prepareStatement("update rating SET win = ?, lose=? , draw=? where player=?");
            query.setInt(1, win);
            query.setInt(2, lose);
            query.setInt(3, draw);
            query.setString(4, nickname);
        } else {
            query = con.prepareStatement("Insert into rating (player,win,lose,draw) values" +
                    "(?,?,?,?)");
            query.setString(1, nickname);
            query.setInt(2, win);
            query.setInt(3, lose);
            query.setInt(4, draw);
        }
        query.execute();
    }
}
