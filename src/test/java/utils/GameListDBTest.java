package utils;

import org.junit.Test;
import test.CopyBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameListDBTest {

    @Test
    public void gameListDB() throws SQLException {

        ArrayList<ResultSet> res = CopyBD.CopyBD();

        ArrayList<String> game = GameListDB.GameListDB();
        for (int i=0;i<game.size();i++){
            System.out.println(game.get(i));
        }

        CopyBD.DeleteBD(res);
    }
}