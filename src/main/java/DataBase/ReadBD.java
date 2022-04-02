package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Class.GameList;
import HelpMethods.PrintArchive;
import HelpMethods.WriteRead;

public class ReadBD implements WriteRead.Read {

    public String Read (String id) {
        String result = "";
        try {
            Connection con = ConnectDB.ConnectDB();
            PreparedStatement query;
            GameList GameList = new GameList();

            query = con.prepareStatement("select * from game where id = ?;");
            query.setInt(1, Integer.parseInt(id));
            ResultSet res = query.executeQuery();
            while (res.next()) {
                GameList.setNickName(res.getString(2), res.getString(3));
                GameList.setCell(FormatStepBD.FormatStepBD(res.getString(4)));
                GameList.setWin(res.getInt(5));
            }
            result = PrintArchive.Print(GameList);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
}
