package repositores.rating;

import org.junit.Test;
import repositores.WriteRead;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class ReadRatingTest {

    // тест чтения общего рейтинга (result.txt)
    @Test
    public void read() throws IOException {

        WriteRead.Read read = new ReadRating();
        String result = read.Read();

        String line,rating="";
        BufferedReader resultRead = new BufferedReader(new FileReader("Result.txt"));
        while ((line = resultRead.readLine()) != null) {
            rating += line + "<br>";
        }

        System.out.println(result);

        assertEquals(rating,result);

    }
}