package repositores.bd;

import model.GameList;
import org.junit.Test;
import repositores.WriteRead;
import test.CopyBD;
import utils.GameListDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class WriteBDTest {

    // Метод проверки записи игрыы в БД. Так как тут используются временные таблицы, записей с такими никнеймами
    // точно нет в БД заранее (класс CopyBD)
    @Test
    public void write() throws SQLException {
        ArrayList<ResultSet> res = CopyBD.CopyBD();

        ArrayList<Integer> a = new ArrayList<>();
        a.addAll(Arrays.asList(1,2,3,5,6,8));
        GameList GameList = new GameList("write1test","write2",a,1);
        WriteRead.Write write = new WriteBD();
        write.Write(GameList);
        Connection con = ConnectBD.ConnectDB();
        PreparedStatement query = con.prepareStatement("select * from game where player1 = 'write1test' and player1 = 'write2'");
        ResultSet result = query.executeQuery();
        while (result.next()){
            assertEquals(GameList.getNickName1(),result.getString(2));
            assertEquals(GameList.getNickName2(),result.getString(3));
            assertEquals(GameList.getCell().toArray(),FormatStepBD.FormatStepBD(result.getString(4)));
            assertEquals(GameList.getWin(),result.getInt(5));
        }

        CopyBD.DeleteBD(res);

    }
}