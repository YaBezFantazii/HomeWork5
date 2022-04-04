package utils;

import model.GameList;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PrintArchiveTest {
    ArrayList<Integer> a;
    ArrayList<Integer> b;
    ArrayList<Integer> c;
    GameList game1;
    GameList game2;
    GameList game3;
    String read;

    @Before
    public void setUp() throws Exception {
        a = new ArrayList<>();
        for (int i=1;i<8;i++){
            a.add(i);
        }
        b = new ArrayList<>();
        b.addAll(Arrays.asList(2,1,3,5,6,9));
        c = new ArrayList<>();
        c.addAll(Arrays.asList(1,2,3,5,8,9,6,4,7));

        game1 = new GameList("test11","test12",a,0);
        game2 = new GameList("test21","test22",b,1);
        game3 = new GameList("test31","test32",c,2);
    }

    // Тест, правильно ли печатаются данные, обработанные в PrintArchive
    @Test
    public void print() throws IOException {
        File file = new File(this.getClass().getResource("/utils/printArchive1.txt").getFile());
        BufferedReader readfile = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
        read = readfile.readLine();
        assertEquals(read, PrintArchive.Print(game1));

        file = new File(this.getClass().getResource("/utils/printArchive2.txt").getFile());
        readfile = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
        read = readfile.readLine();
        assertEquals(read,PrintArchive.Print(game2));

        file = new File(this.getClass().getResource("/utils/printArchive3.txt").getFile());
        readfile = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
        read = readfile.readLine();
        assertEquals(read,PrintArchive.Print(game3));
    }
}