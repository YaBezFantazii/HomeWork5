package HelpMethods;

import java.io.File;
import java.io.FilenameFilter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Class.GameList;
import DataBase.ConnectDB;
import DataBase.FormatStepBD;

public class WriteRead {

    // Наследуется классами WriteJSON, WriteRating, WriteXML, отвечающими за запись в файл и общий рейтинг
    public interface Write {
        String Write(GameList GameList);

    }

    // Наследуется классами ReadJSON, ReadRating, ReadXML, отвечающим за чтение из файлов
    public interface Read {

        String Read(String nameFile);

        // Вспомогательный статический метод, для вывода списка файлов , и выбора, какой файл мы хотим прочитать.
        static String[] ListFiles(String format) {
            String[] files;
            File f = new File(new File("").getAbsolutePath() + "\\"+format);
            // Фильтр, чтобы отображались только файлы с расширением xml/json
            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith("."+format);
                }
            };
            files = f.list(filter);
            return files;
        }

        // Вспомогательный статический метод, для вывода списка игр из Базы Данных
        static ArrayList<String> GameListDB() throws SQLException {

            ArrayList<String> result= new ArrayList<>();
            Connection con = ConnectDB.ConnectDB();
            PreparedStatement query;

            query = con.prepareStatement("select * from game;");
            ResultSet res = query.executeQuery();
            while (res.next()){
               result.add(res.getString(1)+") "+res.getString(2)+"-"+res.getString(3));
            }
            return result;
        }

    }
}
