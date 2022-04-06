package repositores.bd;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repositores.WriteRead;
import test.CopyBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ReadRatingBDTest {

    ArrayList<ResultSet> res;

    // До и после создаем/удаляем временную бд
    @Before
    public void TestBDCreate() throws SQLException {
        this.res = CopyBD.CopyBD();
    }

    @After
    public void TestBDelete() throws SQLException {
        CopyBD.DeleteBD(this.res);
    }

    // Тест метода, которые вытаскивает общий рейтинг из бд
    @Test
    public void read() throws SQLException {

        WriteRead.Read read = new ReadRatingBD();
        System.out.println(read.Read());
        String check = "Побед - поражений - ничьих<br><br>Игрок: test1<br>2-0-5<br><br>Игрок: test2<br>1-4-3<br><br>";
        assertEquals(check,read.Read());

    }
}