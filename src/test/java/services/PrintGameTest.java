package services;

import exceptions.CellCheckException;
import exceptions.PlayerNickLengthException;
import model.GameList;
import org.junit.Assert;
import org.junit.Test;
import services.PrintGame;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

// Тест services/PrintGame - печать поля текущего поля игры, полученного из объекта GameList (от PrintArchive отличает тем,
// что печатает текущей ход, а не все ходы, и имя текущего игрока.
public class PrintGameTest {

    GameList GameList = new GameList();
    ArrayList<Integer> a = new ArrayList<>();
    String checkResult;

    @Test
    public void printGame1() throws PlayerNickLengthException {
        GameList.setNickName("test1","тест2");
        checkResult = "Ход игрока 1 (X) : test1<br> | - | - | - | <br> | - | - | - | <br> | - | - | - |<br><br>";
        Assert.assertEquals(checkResult, PrintGame.PrintGame(GameList));
    }

    @Test
    public void printGame2() throws PlayerNickLengthException, CellCheckException {
        GameList.setNickName("test3","тест4");
        a.addAll(Arrays.asList(1,2,3,4,5));
        GameList.setCell(a);
        checkResult = "Ход игрока 2 (O) : тест4<br> | X | O | X | <br> | O | X | - | <br> | - | - | - |<br><br>";
        assertEquals(checkResult,PrintGame.PrintGame(GameList));
    }

    @Test
    public void printGame3() throws PlayerNickLengthException, CellCheckException {
        GameList.setNickName("test5","тест6");
        a.addAll(Arrays.asList(1,2,3,5,8,9,6,4));
        GameList.setCell(a);
        checkResult = "Ход игрока 1 (X) : test5<br> | X | O | X | <br> | O | O | X | <br> | - | X | O |<br><br>";
        assertEquals(checkResult,PrintGame.PrintGame(GameList));
    }
}