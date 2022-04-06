package repositores.bd;

import exceptions.CellCheckException;
import exceptions.PlayerNickLengthException;
import model.GameList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repositores.WriteRead;
import test.CopyBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ReadGameBDTest {

    ArrayList<ResultSet> res;

    // До и после создаем/удаляем временную бд
    @Before
    public void TestBDCreate() throws SQLException {
        this.res = CopyBD.CopyTimeBD();
    }

    @After
    public void TestBDelete() throws SQLException {
        CopyBD.DeleteTimeBD(this.res);
    }

    // Тест метода, отвечающего за чтение общего рейтинга из бд
    @Test
    public void read() throws SQLException, PlayerNickLengthException, CellCheckException {

        ArrayList<Integer> a = new ArrayList<>();
        a.addAll(Arrays.asList(1,2,3,4,5,6,7));
        GameList GameList = new GameList("test1", "test2" , a , 0);
        WriteRead.ReadFile read = new ReadGameBD();
        GameList GameListBD = read.Read("1");
        assertEquals(GameList.getNickName1(),GameListBD.getNickName1());
        assertEquals(GameList.getNickName2(),GameListBD.getNickName2());
        assertEquals(GameList.getCell(),GameListBD.getCell());
        assertEquals(GameList.getWin(),GameListBD.getWin());

    }
}