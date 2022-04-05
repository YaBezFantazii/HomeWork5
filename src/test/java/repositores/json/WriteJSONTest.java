package repositores.json;

import model.GameList;
import org.junit.Test;
import repositores.WriteRead;
import repositores.xml.WriteXML;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class WriteJSONTest {

    model.GameList GameList = new GameList();
    ArrayList<Integer> a = new ArrayList<>();
    String checkString,check,checkFile="";

    // Победа 1 игрока
    @Test
    public void write1() throws IOException {
        GameList.setNickName("test1","test2");
        a.addAll(Arrays.asList(1,2,3,4,5,6,7));
        GameList.setCell(a);
        GameList.setWin(0);

        WriteRead.WriteFile write = new WriteJSON();

        File f = new File(write.Write(GameList));
        BufferedReader read = new BufferedReader(new FileReader(f));
        while ((check=read.readLine())!=null){
            checkFile+=check;
        }
        read.close();
        f.delete();

        checkString = "{  \"Gameplay\": {    \"Player\": [      {        \"id\": \"1\",        \"name\": \"test1\",        \"symbol\": \"X\"      },      {        \"id\": \"2\",        \"name\": \"test2\",        \"symbol\": \"0\"  " +
                "    }    ],    \"Game\": {      \"Step\": [        {          \"num\": \"1\",          \"playerId\": \"1\",          \"text\": \"1\"        },        {          \"num\": \"2\",          \"playerId\": \"2\",     " +
                "     \"text\": \"2\"        },        {          \"num\": \"3\",          \"playerId\": \"1\",          \"text\": \"3\"        },        {          \"num\": \"4\",          \"playerId\": \"2\",          \"text\": \"4\"        },        {          \"num\": \"5\",          \"playerId\": \"1\",          \"text\": \"5\"        },        {          \"num\": \"6\",          \"playerId\": \"2\",          \"text\": \"6\"        },        {          \"num\": \"7\",          \"playerId\": \"1\",          \"text\": \"7\"        }      ]    },    \"GameResult\": {    " +
                "  \"Player\": {        \"id\": \"1\",        \"name\": \"test1\",        \"symbol\": \"X\"      }    }  }}";

        assertEquals(checkString,checkFile);

    }

    // Победа 2 игрока
    @Test
    public void write2() throws IOException {
        GameList.setNickName("test3","test4");
        a.addAll(Arrays.asList(1,2,3,5,6,8));
        GameList.setCell(a);
        GameList.setWin(1);

        WriteRead.WriteFile write = new WriteJSON();

        File f = new File(write.Write(GameList));
        System.out.println(f.getAbsolutePath());
        BufferedReader read = new BufferedReader(new FileReader(f));
        while ((check=read.readLine())!=null){
            checkFile+=check;
        }
        read.close();
        f.delete();

        checkString = "{  \"Gameplay\": {    \"Player\": [      {        \"id\": \"1\",        \"name\": \"test3\",        \"symbol\": \"X\"      },      {        \"id\": \"2\",        \"name\": \"test4\",        \"symbol\": \"0\"      }    ],    \"Game\": {      \"Step\": [        {          \"num\": \"1\",          \"playerId\": \"1\",          \"text\": \"1\"        },        {          \"num\": \"2\",          \"playerId\": \"2\",          \"text\": \"2\"        },        {          \"num\": \"3\",          \"playerId\": \"1\",          \"text\": \"3\"        },        {          \"num\": \"4\",          \"playerId\": \"2\",          \"text\": \"5\"        },        {          \"num\": \"5\",          \"playerId\": \"1\",          \"text\": \"6\"        },        {          \"num\": \"6\",          \"playerId\": \"2\",          \"text\": \"8\"        }      ]    },    \"GameResult\": {      \"Player\": {        \"id\": \"2\",        \"name\": \"test4\",        \"symbol\": \"O\"      }    }  }}";

        assertEquals(checkString,checkFile);

    }

    // ничья
    @Test
    public void write3() throws IOException {
        GameList.setNickName("test5","test6");
        a.addAll(Arrays.asList(1,2,3,5,8,9,6,4,7));
        GameList.setCell(a);
        GameList.setWin(2);

        WriteRead.WriteFile write = new WriteJSON();

        File f = new File(write.Write(GameList));
        BufferedReader read = new BufferedReader(new FileReader(f));
        while ((check=read.readLine())!=null){
            checkFile+=check;
        }
        read.close();
        f.delete();

        checkString = "{  \"Gameplay\": {    \"Player\": [      {        \"id\": \"1\",        \"name\": \"test5\",        \"symbol\": \"X\"      },      {        \"id\": \"2\",        \"name\": \"test6\",        \"symbol\": \"0\"      }    ],    \"Game\": {      \"Step\": [        {          \"num\": \"1\",          \"playerId\": \"1\",          \"text\": \"1\"        },        {          \"num\": \"2\",          \"playerId\": \"2\",          \"text\": \"2\"        },        {          \"num\": \"3\",          \"playerId\": \"1\",          \"text\": \"3\"        },        {          \"num\": \"4\",          \"playerId\": \"2\",          \"text\": \"5\"        },        {          \"num\": \"5\",          \"playerId\": \"1\",          \"text\": \"8\"        },        {          \"num\": \"6\",          \"playerId\": \"2\",          \"text\": \"9\"        },        {          \"num\": \"7\",          \"playerId\": \"1\",          \"text\": \"6\"        },        {          \"num\": \"8\",          \"playerId\": \"2\",          \"text\": \"4\"        },        {          \"num\": \"9\",          \"playerId\": \"1\",          \"text\": \"7\"        }      ]    },    \"GameResult\": \"Draw!\"  }}";
        assertEquals(checkString,checkFile);

    }
}