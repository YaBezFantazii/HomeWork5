package repositores.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.GameList;
import utils.PrintArchive;
import repositores.WriteRead;

public class ReadBD implements WriteRead.ReadFile {

    // Метод для чтения игры из бд по ее id, и записи ее в String
    public String Read (String id) {
        String result = "";
        try {
            //Подключаемся к бд
            Connection con = ConnectBD.ConnectDB();
            PreparedStatement query;
            // Объект GameList, куда будет записана игра
            GameList GameList = new GameList();

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
            // Записываем игру в переменную с помощью метода PrintArchive.Print в строку для вывода на странице сайта
            result = PrintArchive.Print(GameList);
        } catch (SQLException e) {
            System.out.println(e);
        }

        return result;
    }
}
