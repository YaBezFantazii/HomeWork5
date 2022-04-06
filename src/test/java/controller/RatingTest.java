package controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

// тест сервлета, отвечающего за вывод общего рейтинга из result.txt
@RunWith(MockitoJUnitRunner.class)
public class RatingTest {

    // успешно ли мы обращаемся к rating.jsp
    @Test
    public void doPost() throws ServletException, IOException {
        Rating servlet = new Rating();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);

        Mockito.when(request.getRequestDispatcher("/rating.jsp")).thenReturn(dispatcher);
        servlet.doPost(request,response);
        Mockito.verify(request,Mockito.times(1)).getRequestDispatcher("/rating.jsp");

    }
}