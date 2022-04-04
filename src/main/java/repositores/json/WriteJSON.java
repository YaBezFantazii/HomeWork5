package repositores.json;

import repositores.WriteRead;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.GameList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WriteJSON implements WriteRead.WriteFile {

    public String Write (GameList GameList) {
        try {
            // Запись происходит при помощи библиотеки gson
            // Делаем запись удобной для чтения человека (.setPrettyPrinting()) и
            // переопредеялем сериализацию для соответствования образцу (.registerTypeAdapter)
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(GameList.getClass(), new serialize(){
                    })
                    .create();
            String json = gson.toJson(GameList);

            // Если папки json не существует, то создаем ее
            File theDir = new File("JSON\\");
            if (!theDir.exists()){
                theDir.mkdirs();
            }

            // Записываем данные в папку JSON
            String result = "JSON\\gameplay1.json";
            File file1 = new File(result);
            int i=1;
            // Если есть файл gameplay1.json, по проверяем существует ли gameplay2.gson, потом gameplay3.gson
            // и так далее ( gameplay(i+1).json ), пока не сможем создать новый файл.
            while (file1.exists()){
                result = "JSON\\gameplay"+i+".json";
                file1 = new File(result);
                i++;
            }
            BufferedWriter resultWrite = new BufferedWriter(new FileWriter(result, StandardCharsets.UTF_8));
            resultWrite.write(json);
            resultWrite.flush();
            resultWrite.close();

            // Возвращаем путь к созданному файлу
            return System.getProperty("user.dir")+"\\"+result;

        } catch (IOException e) {
            System.out.println(e);
            return "error";
        }
    }
}
