package repositores.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.CellCheckException;
import exceptions.PlayerNickLengthException;
import model.GameList;
import repositores.WriteRead;

public class ReadGameBD implements WriteRead.ReadFile {

    // Метод для чтения игры из бд по ее id, и записи ее в String
    public GameList Read (String id) {
        String result = "";
        // Объект GameList, куда будет записана игра
        GameList GameList = new GameList();
        try {
            //Подключаемся к бд
            Connection con = ConnectBD.ConnectDB();
            PreparedStatement query;

            // Получаем игру по ее id
            query = con.prepareStatement("select * from game where id = ?;");
            query.setInt(1, Integer.parseInt(id));
            ResultSet res = query.executeQuery();
            // Получаем данные игры из запроса и записываем их в GameList
            while (res.next()) {
                GameList.setNickName(res.getString(2), res.getString(3));
                GameList.setCell(FormatStepBD.FormatStepBD(res.getString(4)));
                GameList.setWin(res.getInt(5));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (PlayerNickLengthException e){
            System.out.println(e);
        } catch (CellCheckException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return GameList;
    }
}
