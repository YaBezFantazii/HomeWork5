package repositores.bd;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class ConnectBDTest {

    // тест соединения с бд
    @Test
    public void connectDB() {
        Connection connect = null;
        connect = ConnectBD.ConnectDB();
        assertTrue(connect!=null);
    }
}