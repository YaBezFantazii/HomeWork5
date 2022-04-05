package repositores.rating;

import model.GameList;
import org.junit.Test;
import repositores.WriteRead;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class WriteRatingTest {

    // тест записи в общий рейтинг
    GameList GameList;
    ArrayList<Integer> a = new ArrayList<>();

    // победа 1 игрока
    @Test
    public void write1() throws IOException {
        String line,rating="";
        a.addAll(Arrays.asList(1,2,3,4,5,6,7));
        GameList = new GameList("test1","тест2",a,0);

        WriteRead.Write write = new WriteRating();
        write.Write(GameList);

        BufferedReader resultRead = new BufferedReader(new FileReader("Result.txt"));
        while ((line = resultRead.readLine()) != null) {
            rating += line + "<br>";
        }

        WriteRead.Read read = new ReadRating();

        assertEquals(rating,read.Read());

    }

    // победа 2 игрока
    @Test
    public void write2() throws IOException {
        String line,rating="";
        a.addAll(Arrays.asList(1,2,3,5,6,8));
        GameList = new GameList("test3","тест2",a,1);

        WriteRead.Write write = new WriteRating();
        write.Write(GameList);

        BufferedReader resultRead = new BufferedReader(new FileReader("Result.txt"));
        while ((line = resultRead.readLine()) != null) {
            rating += line + "<br>";
        }

        WriteRead.Read read = new ReadRating();

        assertEquals(rating,read.Read());

    }

    // ничья
    @Test
    public void write3() throws IOException {
        String line,rating="";
        a.addAll(Arrays.asList(1,2,3,5,8,9,6,4,7));
        GameList = new GameList("test4","тест2",a,2);

        WriteRead.Write write = new WriteRating();
        write.Write(GameList);

        BufferedReader resultRead = new BufferedReader(new FileReader("Result.txt"));
        while ((line = resultRead.readLine()) != null) {
            rating += line + "<br>";
        }

        WriteRead.Read read = new ReadRating();

        assertEquals(rating,read.Read());

    }
}