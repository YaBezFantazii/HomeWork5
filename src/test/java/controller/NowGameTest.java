package controller;

import model.GameList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class NowGameTest {

    NowGame servlet = new NowGame();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    HttpSession session = Mockito.mock(HttpSession.class);
    RequestDispatcher dispatcher = Mockito.mock((RequestDispatcher.class));

    // Метод Post , не введены имена игроков
    @Test
    public void doPost1() throws ServletException, IOException {

        Mockito.when(request.getSession()).thenReturn(session);

        servlet.doPost(request,response);
        Mockito.verify(response,Mockito.times(1)).sendRedirect("/gameplay/playerNick.jsp");
    }

    // Метод Post, введено неккоректное число в поле выбора ячейки
    @Test
    public void doPost2() throws ServletException, IOException {

        GameList game = new GameList();
        game.setNickName("222","333");

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("GameList")).thenReturn(game);
        Mockito.when(request.getParameter("nowGame")).thenReturn("25");

        Mockito.when(request.getRequestDispatcher("/nowGame.jsp")).thenReturn(dispatcher);
        servlet.doPost(request,response);
    }

    // Метод Post, выбранная ячейка занята
    @Test
    public void doPost3() throws ServletException, IOException {

        GameList game = new GameList();
        game.setNickName("222","333");
        game.setCellId(5);

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("GameList")).thenReturn(game);
        Mockito.when(request.getParameter("nowGame")).thenReturn("5");

        Mockito.when(request.getRequestDispatcher("/nowGame.jsp")).thenReturn(dispatcher);
        servlet.doPost(request,response);
    }

    // Метод Post, успешное завершение игры (победа 1 игрока)
    @Test
    public void doPost4() throws ServletException, IOException {

        GameList game = new GameList();
        game.setNickName("222","333");

        for (int i=1;i<8;i++){
            Mockito.when(request.getSession()).thenReturn(session);
            Mockito.when(session.getAttribute("GameList")).thenReturn(game);
            Mockito.when(request.getParameter("nowGame")).thenReturn(Integer.toString(i));

            Mockito.when(request.getRequestDispatcher("/nowGame.jsp")).thenReturn(dispatcher);
            if (i==7){
                Mockito.when(request.getRequestDispatcher("/resultGame.jsp")).thenReturn(dispatcher);
            }
            servlet.doPost(request,response);
        }
        System.out.println("Игрок 1 победил");

    }

    // Метод Post, успешное завершение игры (победа 2 игрока)
    @Test
    public void doPost5() throws ServletException, IOException {

        GameList game = new GameList();
        ArrayList<Integer> c = new ArrayList<>();
        c.addAll(Arrays.asList(2,1,3,5,6));
        game.setNickName("222","333");
        game.setCell(c);

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("GameList")).thenReturn(game);
        Mockito.when(request.getParameter("nowGame")).thenReturn(Integer.toString(9));

        Mockito.when(request.getRequestDispatcher("/resultGame.jsp")).thenReturn(dispatcher);
        servlet.doPost(request,response);
        System.out.println("Игрок 2 победил");
        }


    // Метод Post, успешное завершение игры (ничья)
    @Test
    public void doPost6() throws ServletException, IOException {

        GameList game = new GameList();
        ArrayList<Integer> c = new ArrayList<>();
        c.addAll(Arrays.asList(1,2,3,5,8,9,6,4));
        game.setNickName("222","333");
        game.setCell(c);

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("GameList")).thenReturn(game);
        Mockito.when(request.getParameter("nowGame")).thenReturn(Integer.toString(7));

        Mockito.when(request.getRequestDispatcher("/resultGame.jsp")).thenReturn(dispatcher);
        servlet.doPost(request,response);
        System.out.println("Ничья");
    }



    // Метод Get , не введены имена игроков
    @Test
    public void doGet1() throws ServletException, IOException {

        Mockito.when(request.getSession()).thenReturn(session);

        servlet.doGet(request,response);
        Mockito.verify(response,Mockito.times(1)).sendRedirect("/gameplay/playerNick.jsp");
    }

    // Метод Get, имена игроков в порядке
    @Test
    public void doGet2() throws ServletException, IOException {

        GameList game = new GameList();
        game.setNickName("222","333");

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("GameList")).thenReturn(game);
        Mockito.when(request.getParameter("nowGame")).thenReturn("");

        Mockito.when(request.getRequestDispatcher("/nowGame.jsp")).thenReturn(dispatcher);
        servlet.doGet(request,response);
    }
}