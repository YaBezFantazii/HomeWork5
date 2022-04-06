package utils;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;

import static org.junit.Assert.*;

public class ListFilesTest {

    String[] files;

    // Тест метода для вывода списка файлов
    // xml
    @Test
    public void listFiles1() {
        File f = new File(new File("").getAbsolutePath() + "\\xml");
        // Фильтр, чтобы отображались только файлы с расширением xml/json
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".xml");
            }
        };
        files = f.list(filter);
        System.out.println("Кол-во файлов в папке: "+f.getAbsolutePath()+"\\xml = "+files.length);
        assertArrayEquals(files,ListFiles.ListFiles("xml"));
    }

    // Тест метода для вывода списка файлов
    // json
    @Test
    public void listFiles2() {
        File f = new File(new File("").getAbsolutePath() + "\\json");
        // Фильтр, чтобы отображались только файлы с расширением xml/json
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".json");
            }
        };
        files = f.list(filter);
        System.out.println("Кол-во файлов в папке: "+f.getAbsolutePath()+"\\json = "+files.length);
        assertArrayEquals(files,ListFiles.ListFiles("json"));
    }
}