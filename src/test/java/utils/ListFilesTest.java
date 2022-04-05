package utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListFilesTest {

    String[] testXML = new String[]{"gameplay1.xml","gameplay2.xml","gameplay3.xml"};
    String[] testJSON = new String[]{"gameplay1.json","gameplay2.json","gameplay3.json"};

    // Тест метода для вывода списка файлов
    // xml
    @Test
    public void listFiles1() {
        assertArrayEquals(testXML,ListFiles.ListFiles("xml"));
    }

    // Тест метода для вывода списка файлов
    // json
    @Test
    public void listFiles2() {
        assertArrayEquals(testJSON,ListFiles.ListFiles("json"));
    }
}