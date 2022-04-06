package repositores.xml;

import exceptions.CellCheckException;
import exceptions.PlayerNickLengthException;
import model.GameList;
import org.junit.Test;
import repositores.WriteRead;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

// тест чтения из xml файла и печати данных об игре (ReadXNL, PrintArchive)
public class ReadXMLTest {

    GameList GameList;
    ArrayList<Integer> a = new ArrayList<>();

    // Победа 1 игрока
    @Test
    public void read1() throws IOException, PlayerNickLengthException, CellCheckException {

        String xml = "gameplay1.xml";
        a.addAll(Arrays.asList(1,2,3,4,5,6,7));
        GameList = new GameList("test1","test2",a,0);

        WriteRead.ReadFile readXml = new ReadXML();

        assertEquals(GameList.getNickName1(),readXml.Read(xml).getNickName1());
        assertEquals(GameList.getNickName2(),readXml.Read(xml).getNickName2());
        assertEquals(GameList.getCell(),readXml.Read(xml).getCell());
        assertEquals(GameList.getWin(),readXml.Read(xml).getWin());

    }

    // Победа 2 игрока
    @Test
    public void read2() throws IOException, PlayerNickLengthException, CellCheckException {

        String xml = "gameplay2.xml";
        a.addAll(Arrays.asList(1,2,3,5,7,6,9,4));
        GameList = new GameList("test2","test3",a,1);

        WriteRead.ReadFile readXml = new ReadXML();

        assertEquals(GameList.getNickName1(),readXml.Read(xml).getNickName1());
        assertEquals(GameList.getNickName2(),readXml.Read(xml).getNickName2());
        assertEquals(GameList.getCell(),readXml.Read(xml).getCell());
        assertEquals(GameList.getWin(),readXml.Read(xml).getWin());

    }

    // ничья
    @Test
    public void read3() throws IOException, PlayerNickLengthException, CellCheckException {

        String xml = "gameplay3.xml";
        a.addAll(Arrays.asList(1,2,3,5,8,9,6,4,7));
        GameList = new GameList("1","12",a,2);

        WriteRead.ReadFile readXml = new ReadXML();

        assertEquals(GameList.getNickName1(),readXml.Read(xml).getNickName1());
        assertEquals(GameList.getNickName2(),readXml.Read(xml).getNickName2());
        assertEquals(GameList.getCell(),readXml.Read(xml).getCell());
        assertEquals(GameList.getWin(),readXml.Read(xml).getWin());

    }
    }
