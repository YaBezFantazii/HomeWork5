package exceptions;

import model.GameList;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.*;

// Тест класса ошибок, связанных с именами игроков
public class PlayerNickLengthExceptionTest {

    // Имя слишком длинное
    @Test
    public void VeryLengthNick() {

        GameList GameList = new GameList();
        String player1= "12";
        String player2= "wqesadafacsdfsdfcsdcfsfsdfcasfsdfsbfdsbvfbdfasdfdsfasdfcxfsdfsvcxvasfcxfdsfsbfscxxcvbsdfbcvxvsdfcvxvsdf";
        try {
            GameList.setNickName(player1,player2);
        } catch (PlayerNickLengthException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    // Одно или оба имена пусты
    @Test
    public void NullLengthNick() {

        GameList GameList = new GameList();
        String player1= "12";
        String player2= "  ";
        try {
            GameList.setNickName(player1,player2);
        } catch (PlayerNickLengthException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}