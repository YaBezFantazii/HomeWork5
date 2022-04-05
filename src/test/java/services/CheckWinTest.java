package services;

import model.GameList;
import org.junit.Assert;
import org.junit.Test;
import services.CheckWin;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

// Тесты на проверку условия победы в процессе игры
public class CheckWinTest {

    GameList GameList = new GameList();
    ArrayList<Integer> a;

    // | x | 0 | x |
    // | 0 | x | 0 |
    // | x | - | - |
    @Test
    public void checkWin1() {
        for (int i=1;i<8;i++){
            GameList.setCellId(i);
        }
       Assert.assertEquals(true, CheckWin.CheckWin(GameList));
    }

    // | 0 | x | x |
    // | - | 0 | x |
    // | - | - | 0 |
    @Test
    public void checkWin2() {
        a = new ArrayList<>();
        a.addAll(Arrays.asList(2,1,3,5,6,9));
        GameList.setCell(a);
        assertEquals(true,CheckWin.CheckWin(GameList));
    }

    // | x | 0 | x |
    // | x | 0 | x |
    // | 0 | x | 0 |
    @Test
    public void checkWin3() {
        a = new ArrayList<>();
        a.addAll(Arrays.asList(1,2,3,5,8,9,6,4,7));
        GameList.setCell(a);
        assertEquals(false,CheckWin.CheckWin(GameList));
    }
}