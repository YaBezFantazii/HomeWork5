package utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.CopyBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

// тест показа списка игр из БД
public class GameListDBTest {

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

    @Test
    public void gameListDB() throws SQLException {

        ArrayList<String> game = GameListDB.GameListDB();

        assertEquals("1) test1-test2",game.get(0));
        assertEquals("2) max-тест4",game.get(1));
    }
}