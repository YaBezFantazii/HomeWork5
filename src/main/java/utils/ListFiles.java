package utils;

import java.io.File;
import java.io.FilenameFilter;

public class ListFiles {

    // Вспомогательный статический метод, для вывода списка файлов
    public static String[] ListFiles(String format) {
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
