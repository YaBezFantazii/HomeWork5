package repositores.bd;

import org.junit.Test;
import repositores.WriteRead;
import test.CopyBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ReadRatingBDTest {

    // Тест метода, которые вытаскивает общий рейтинг из бд
    @Test
    public void read() throws SQLException {

        ArrayList<ResultSet> res = CopyBD.CopyBD();

        WriteRead.Read read = new ReadRatingBD();
        System.out.println(read.Read());
        String check = "Побед - поражений - ничьих<br><br>Игрок: test1<br>2-0-5<br><br>Игрок: test2<br>1-4-3<br><br>";
        assertEquals(check,read.Read());

        CopyBD.DeleteBD(res);
    }
}