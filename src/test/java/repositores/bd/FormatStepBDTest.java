package repositores.bd;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

// Тест 2 перегруженных методов из класса FormatStepBD
public class FormatStepBDTest {

    //Тест метод для преобразования шагов игры (в бд он записаны как String) в массив ArrayList для запись в класс GameList
    @Test
    public void formatStepBD() {
        String test = "1234567";
        ArrayList<Integer> a = new ArrayList<>();
        a.addAll(Arrays.asList(1,2,3,4,5,6,7));
        assertArrayEquals(a.toArray(),FormatStepBD.FormatStepBD(test).toArray());
    }

    // Тест перегруженный метода для преобразования шагов игры (в GameList они записаны как ArrayList)
    // в единую строку для записи в БД
    @Test
    public void testFormatStepBD() {
        ArrayList<Integer> a = new ArrayList<>();
        a.addAll(Arrays.asList(1,2,3,4,5,6,7));
        assertEquals("1234567",FormatStepBD.FormatStepBD(a));
    }
}