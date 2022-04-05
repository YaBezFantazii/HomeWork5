package repositores.xml;

import model.GameList;
import org.junit.Test;
import repositores.WriteRead;
import repositores.xml.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

// тест записи файл в xml (файл удаляется)
public class WriteXMLTest {

    GameList GameList = new GameList();
    ArrayList<Integer> a = new ArrayList<>();
    String checkString,check,checkFile="";

    // Победа 1 игрока
    @Test
    public void write1() throws IOException {
        GameList.setNickName("test1","test2");
        a.addAll(Arrays.asList(1,2,3,4,5,6,7));
        GameList.setCell(a);
        GameList.setWin(0);

        WriteRead.WriteFile write = new WriteXML();

        File f = new File(write.Write(GameList));
        BufferedReader read = new BufferedReader(new FileReader(f));
        while ((check=read.readLine())!=null){
            checkFile+=check;
        }
        read.close();
        f.delete();

        checkString = "<?xml version=\"1.0\" encoding=\"windows-1251\"?><Gameplay>" +
                "    <Player id=\"1\" name=\"test1\" symbol=\"X\"/>" +
                "    <Player id=\"2\" name=\"test2\" symbol=\"O\"/>" +
                "    <Game>" +
                "        <Step num=\"1\" playerId=\"1\">1</Step>" +
                "        <Step num=\"2\" playerId=\"2\">2</Step>" +
                "        <Step num=\"3\" playerId=\"1\">3</Step>" +
                "        <Step num=\"4\" playerId=\"2\">4</Step>" +
                "        <Step num=\"5\" playerId=\"1\">5</Step>" +
                "        <Step num=\"6\" playerId=\"2\">6</Step>" +
                "        <Step num=\"7\" playerId=\"1\">7</Step>" +
                "    </Game>" +
                "    <GameResult>" +
                "        <Player id=\"1\" name=\"test1\" symbol=\"X\"/>" +
                "    </GameResult>" +
                "</Gameplay>";

        assertEquals(checkString,checkFile);

    }

    // Победа 2 игрока
    @Test
    public void write2() throws IOException {
        GameList.setNickName("test3","test4");
        a.addAll(Arrays.asList(1,2,3,5,6,8));
        GameList.setCell(a);
        GameList.setWin(1);

        WriteRead.WriteFile write = new WriteXML();

        File f = new File(write.Write(GameList));
        System.out.println(f.getAbsolutePath());
        BufferedReader read = new BufferedReader(new FileReader(f));
        while ((check=read.readLine())!=null){
            checkFile+=check;
        }
        read.close();
        f.delete();

        checkString = "<?xml version=\"1.0\" encoding=\"windows-1251\"?><Gameplay>" +
                "    <Player id=\"1\" name=\"test3\" symbol=\"X\"/>" +
                "    <Player id=\"2\" name=\"test4\" symbol=\"O\"/>" +
                "    <Game>" +
                "        <Step num=\"1\" playerId=\"1\">1</Step>" +
                "        <Step num=\"2\" playerId=\"2\">2</Step>" +
                "        <Step num=\"3\" playerId=\"1\">3</Step>" +
                "        <Step num=\"4\" playerId=\"2\">5</Step>" +
                "        <Step num=\"5\" playerId=\"1\">6</Step>" +
                "        <Step num=\"6\" playerId=\"2\">8</Step>" +
                "    </Game>" +
                "    <GameResult>" +
                "        <Player id=\"2\" name=\"test4\" symbol=\"O\"/>" +
                "    </GameResult>" +
                "</Gameplay>";

        assertEquals(checkString,checkFile);

    }

    // ничья
    @Test
    public void write3() throws IOException {
        GameList.setNickName("test5","test6");
        a.addAll(Arrays.asList(1,2,3,5,8,9,6,4,7));
        GameList.setCell(a);
        GameList.setWin(2);

        WriteRead.WriteFile write = new WriteXML();

        File f = new File(write.Write(GameList));
        BufferedReader read = new BufferedReader(new FileReader(f));
        while ((check=read.readLine())!=null){
            checkFile+=check;
        }
        read.close();
        f.delete();

        checkString = "<?xml version=\"1.0\" encoding=\"windows-1251\"?><Gameplay>" +
                "    <Player id=\"1\" name=\"test5\" symbol=\"X\"/>" +
                "    <Player id=\"2\" name=\"test6\" symbol=\"O\"/>" +
                "    <Game>" +
                "        <Step num=\"1\" playerId=\"1\">1</Step>" +
                "        <Step num=\"2\" playerId=\"2\">2</Step>" +
                "        <Step num=\"3\" playerId=\"1\">3</Step>" +
                "        <Step num=\"4\" playerId=\"2\">5</Step>" +
                "        <Step num=\"5\" playerId=\"1\">8</Step>" +
                "        <Step num=\"6\" playerId=\"2\">9</Step>" +
                "        <Step num=\"7\" playerId=\"1\">6</Step>" +
                "        <Step num=\"8\" playerId=\"2\">4</Step>" +
                "        <Step num=\"9\" playerId=\"1\">7</Step>" +
                "    </Game>" +
                "    <GameResult>Draw!</GameResult>" +
                "</Gameplay>";

        assertEquals(checkString,checkFile);

    }
}