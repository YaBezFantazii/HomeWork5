package Rating;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ReadRating {


    // Вытаскивает информацию из файла Result.txt (общий рейтинг) и возвращает ее как 1 строку
    public static String ReadFile() {

        String line,rating="";
        File file1 = new File("Result.txt");

        try {
            if (!file1.exists() || file1.length() == 0) {
                BufferedWriter resultWrite1 = new BufferedWriter(new FileWriter("Result.txt", StandardCharsets.UTF_8));
                resultWrite1.write("Побед - поражений - ничьих\r\n\r\n");
                resultWrite1.flush();
                resultWrite1.close();
            }
            BufferedReader resultRead = new BufferedReader(new FileReader("Result.txt", StandardCharsets.UTF_8));

            while ((line = resultRead.readLine()) != null) {
                rating += line + "<br>";
            }
            return rating;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return "Нет рейтинга";
        }
    }



}
