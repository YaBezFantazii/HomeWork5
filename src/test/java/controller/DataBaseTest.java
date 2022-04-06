package controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import repositores.bd.CheckNewTableBD;
import test.CopyBD;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

// Тест сервлета, отвечающего за работу с бд
@RunWith(MockitoJUnitRunner.class)
public class DataBaseTest {

    DataBase servlet = new DataBase();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    RequestDispatcher dispatcher = Mockito.mock((RequestDispatcher.class));
    ArrayList<ResultSet> res;

    // До и после создаем/удаляем временную бд
    @Before
    public void TestBDCreate() throws SQLException {
        this.res = CopyBD.CopyBD();
    }

    @After
    public void TestBDelete() throws SQLException {
        CopyBD.DeleteBD(this.res);
    }

    // Общий рейтинг без выбранной игры для чтения
    @Test
    public void doPost() throws ServletException, IOException{
        Mockito.when(request.getParameter("command")).thenReturn("1");
        Mockito.when(request.getParameter("id")).thenReturn("");

        Mockito.when(request.getRequestDispatcher("/dataBase.jsp")).thenReturn(dispatcher);
        servlet.doPost(request,response);
    }

    // Общий рейтинг с выбранной игры для чтения
    @Test
    public void doPost2() throws ServletException, IOException{
        Mockito.when(request.getParameter("command")).thenReturn("1");
        Mockito.when(request.getParameter("id")).thenReturn("1");

        Mockito.when(request.getRequestDispatcher("/dataBase.jsp")).thenReturn(dispatcher);
        servlet.doPost(request,response);
    }

    // Без общего рейтинга без выбранной игрой для чтения
    @Test
    public void doPost3() throws ServletException, IOException{
        Mockito.when(request.getParameter("id")).thenReturn("");

        Mockito.when(request.getRequestDispatcher("/dataBase.jsp")).thenReturn(dispatcher);
        servlet.doPost(request,response);
    }

    // Без общего рейтинга с выбранной игрой для чтения
    @Test
    public void doPost4() throws ServletException, IOException{
        Mockito.when(request.getParameter("id")).thenReturn("1");

        Mockito.when(request.getRequestDispatcher("/dataBase.jsp")).thenReturn(dispatcher);
        servlet.doPost(request,response);
    }


    @Test
    public void doGet() throws ServletException, IOException {
        Mockito.when(request.getRequestDispatcher("/dataBase.jsp")).thenReturn(dispatcher);
        servlet.doGet(request,response);
    }
}