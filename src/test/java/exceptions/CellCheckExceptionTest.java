package exceptions;

import model.GameList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CellCheckExceptionTest {

    // больше 9 ячеек в массиве cell класса GameList
    @Test
    public void CellCheckExceptionTest1() {

        GameList GameList = new GameList();
        ArrayList<Integer> a = new ArrayList<>();
        a.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        try {
            GameList.setCell(a);
        } catch (CellCheckException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }

    // Есть повторяющиеся значения в массиве cell класса GameList
    @Test
    public void CellCheckExceptionTest2() {

        GameList GameList = new GameList();
        ArrayList<Integer> a = new ArrayList<>();
        a.addAll(Arrays.asList(1,2,3,4,5,5,7,8,9));
        try {
            GameList.setCell(a);
        } catch (CellCheckException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }

    // Неккоретное значение переменной win класса GameList
    @Test
    public void CellCheckExceptionTest3() {

        GameList GameList = new GameList();
        try {
            GameList.setWin(4);
        } catch (CellCheckException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }


}