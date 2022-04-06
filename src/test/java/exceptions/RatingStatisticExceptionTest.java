package exceptions;

import model.GameList;
import model.Rating;
import org.junit.Test;

import static org.junit.Assert.*;

public class RatingStatisticExceptionTest {

    // Кол-во побед и поражений меньше 0 (создаем через конструктор)
    @Test
    public void RatingStatisticExceptionTest() {
        try{
            Rating rating = new Rating("player1",0,-1,2);
        } catch (RatingStatisticException e){
            System.out.println(e);
            e.printStackTrace();
        } catch (PlayerNickLengthException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    // Изменяем через сеттер кол-во ничьих (некорреткное)
    @Test
    public void RatingStatisticExceptionTest2() {
        try{
            Rating rating = new Rating("player1",1,0,2);
            rating.setDeadHeat(-1);
        } catch (RatingStatisticException e){
            System.out.println(e);
            e.printStackTrace();
        } catch (PlayerNickLengthException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    }
