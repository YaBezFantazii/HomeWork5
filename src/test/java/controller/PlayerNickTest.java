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

// Тест сервлета, отвечающего за вводи имен игроков
@RunWith(MockitoJUnitRunner.class)
public class PlayerNickTest {

    PlayerNick servlet = new PlayerNick();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    HttpSession session = Mockito.mock(HttpSession.class);
    RequestDispatcher dispatcher = Mockito.mock((RequestDispatcher.class));


    // Правильно заданы никнеймы, нет старой сесии
    @Test
    public void doPost1() throws ServletException, IOException {
        GameList game = new GameList();

        Mockito.when(request.getParameter("nick1")).thenReturn("123");
        Mockito.when(request.getParameter("nick2")).thenReturn("1234");
        Mockito.when(request.getSession()).thenReturn(session);

        servlet.doPost(request,response);
        Mockito.verify(response,Mockito.times(1)).sendRedirect("/gameplay/nowGame");

    }

    // Правильно заданы никнеймы, есть старая сесия
    @Test
    public void doPost2() throws ServletException, IOException {
        GameList game = new GameList();

        Mockito.when(request.getParameter("nick1")).thenReturn("123");
        Mockito.when(request.getParameter("nick2")).thenReturn("1234");
        Mockito.when(session.getAttribute("GameList")).thenReturn(game);
        Mockito.when(request.getSession()).thenReturn(session);

        servlet.doPost(request,response);
        Mockito.verify(response,Mockito.times(1)).sendRedirect("/gameplay/nowGame");

    }

    // Неккоретно заданы ники
    @Test
    public void doPost3() throws ServletException, IOException {
        Mockito.when(request.getParameter("nick1")).thenReturn("123");
        Mockito.when(request.getParameter("nick2")).thenReturn("123");

        Mockito.when(request.getRequestDispatcher("/playerNick.jsp")).thenReturn(dispatcher);
        servlet.doPost(request,response);
        request.getRequestDispatcher("/playerNick.jsp").forward(request, response);

    }
}