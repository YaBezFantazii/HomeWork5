package HelpMethods;

import java.io.File;
import java.io.FilenameFilter;
import Class.GameList;
import JSON.ReadJSON;

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

    }
}
