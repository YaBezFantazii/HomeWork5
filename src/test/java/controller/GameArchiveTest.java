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

// Тест сервлета, отвечающего чтение xml и json файлов
@RunWith(MockitoJUnitRunner.class)
public class GameArchiveTest {

    GameArchive servlet = new GameArchive();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    RequestDispatcher dispatcher = Mockito.mock((RequestDispatcher.class));

    // читаем XML (корневая_папка_проекта/json/gameplay1.xml)
    @Test
    public void doPost() throws ServletException, IOException {
        Mockito.when(request.getParameter("number")).thenReturn("1");
        Mockito.when(request.getRequestDispatcher("/gameArchive.jsp")).thenReturn(dispatcher);
        servlet.doPost(request,response);
    }

    // читаем JSON (корневая_папка_проекта/json/gameplay2.json)
    @Test
    public void doPost2() throws ServletException, IOException {
        Mockito.when(request.getParameter("number")).thenReturn("4");
        Mockito.when(request.getRequestDispatcher("/gameArchive.jsp")).thenReturn(dispatcher);
        servlet.doPost(request,response);
    }

    // файл не выбран
    @Test
    public void doPost3() throws ServletException, IOException {
        Mockito.when(request.getParameter("number")).thenReturn("");
        Mockito.when(request.getRequestDispatcher("/gameArchive.jsp")).thenReturn(dispatcher);
        servlet.doPost(request,response);
    }

    // введеный номер файла за пределами кол-ва файлов
    @Test
    public void doPost4() throws ServletException, IOException {
        Mockito.when(request.getParameter("number")).thenReturn("25");
        Mockito.when(request.getRequestDispatcher("/gameArchive.jsp")).thenReturn(dispatcher);
        servlet.doPost(request,response);
    }

    @Test
    public void doGet() throws ServletException, IOException {

        Mockito.when(request.getRequestDispatcher("/gameArchive.jsp")).thenReturn(dispatcher);
        servlet.doGet(request,response);
    }
}