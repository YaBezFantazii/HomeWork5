package repositores.json;

import exceptions.CellCheckException;
import exceptions.PlayerNickLengthException;
import model.GameList;
import org.junit.Test;
import repositores.WriteRead;
import repositores.xml.ReadXML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

// тест класса, отвечающего за чтение json файлов
public class ReadJSONTest {

    model.GameList GameList;
    ArrayList<Integer> a = new ArrayList<>();

    // Победа 1 игрока
    @Test
    public void read1() throws IOException, PlayerNickLengthException, CellCheckException {

        String xml = "gameplay1.json";
        a.addAll(Arrays.asList(1,2,3,4,5,6,7));
        GameList = new GameList("test1","test2",a,0);

        WriteRead.ReadFile readjson = new ReadJSON();

        assertEquals(GameList.getNickName1(),readjson.Read(xml).getNickName1());
        assertEquals(GameList.getNickName2(),readjson.Read(xml).getNickName2());
        assertEquals(GameList.getCell(),readjson.Read(xml).getCell());
        assertEquals(GameList.getWin(),readjson.Read(xml).getWin());

    }

    // Победа 2 игрока
    @Test
    public void read2() throws IOException, PlayerNickLengthException, CellCheckException {

        String xml = "gameplay2.json";
        a.addAll(Arrays.asList(1,2,3,5,7,6,9,4));
        GameList = new GameList("test2","test3",a,1);

        WriteRead.ReadFile readjson = new ReadJSON();

        assertEquals(GameList.getNickName1(),readjson.Read(xml).getNickName1());
        assertEquals(GameList.getNickName2(),readjson.Read(xml).getNickName2());
        assertEquals(GameList.getCell(),readjson.Read(xml).getCell());
        assertEquals(GameList.getWin(),readjson.Read(xml).getWin());

    }

    // ничья
    @Test
    public void read3() throws IOException, PlayerNickLengthException, CellCheckException {

        String xml = "gameplay3.json";
        a.addAll(Arrays.asList(1,2,3,5,8,9,6,4,7));
        GameList = new GameList("1","12",a,2);

        WriteRead.ReadFile readjson = new ReadJSON();

        assertEquals(GameList.getNickName1(),readjson.Read(xml).getNickName1());
        assertEquals(GameList.getNickName2(),readjson.Read(xml).getNickName2());
        assertEquals(GameList.getCell(),readjson.Read(xml).getCell());
        assertEquals(GameList.getWin(),readjson.Read(xml).getWin());

    }
}