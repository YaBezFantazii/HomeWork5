package repositores.bd;

import repositores.WriteRead;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadRatingBD implements WriteRead.Read {

    public String Read(){
        String result="Побед - поражений - ничьих<br><br>";
        try {
            //Подключаемся к бд
            Connection con = ConnectBD.ConnectDB();
            // Получаем рейтинг игроков
            PreparedStatement query = con.prepareStatement("select * from rating");
            ResultSet res = query.executeQuery();
            while (res.next()){
                result += "Игрок: "+res.getString(1)+"<br>";
                result += res.getInt(2) +"-"+ res.getInt(3) +"-"+ res.getInt(4)+"<br><br>";
            }
            con.close();

        } catch (SQLException e){
            System.out.println(e);
        }
        return result;
    }
}
