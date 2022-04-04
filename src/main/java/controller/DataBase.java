package controller;

import repositores.WriteRead;
import repositores.bd.ReadBD;
import repositores.bd.CheckNewTableBD;
import repositores.rating.ReadRating;
import utils.GameListDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataBase extends HttpServlet {

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        request.setAttribute("game", "");
        request.setAttribute("rating","");
        ArrayList<String> GameList = new ArrayList<>();
        String b;

        // Если на сайте выбран флажок "Общий рейтинг", то показываем его
        if(request.getParameter("command")!=null){
            WriteRead.Read read = new ReadRating();
            request.setAttribute("rating",  read.Read());
            System.out.println("Показываем общий рейтинг"); // sout для теста
        }

        // Получаем список игр из БД
        try {
                GameList = GameListDB.GameListDB();
            } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("GameList", GameList);
        // Получаем id игры
        String id = request.getParameter("id");
        // Если мы не вводили id игры, то ничего.
        if (request.getParameter("id").equals("") || request.getParameter("id").equals(null)) {
            b = "";
        } else {
        // Читаем из БД запись о игре по ее id
            WriteRead.ReadFile read = new ReadBD();
            b = read.Read(id);
            System.out.println("Показываем выбранную игру"); // sout для теста
        }
        // Передаем полученную игру в jsp
        request.setAttribute("game", b);
        System.out.println("Тест (Post)"); // sout для тестов
        request.getRequestDispatcher("/dataBase.jsp").forward(request, response);

    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setAttribute("game", "");
        request.setAttribute("rating","");
        // Проверка на то, созданы ли таблицы.
        CheckNewTableBD.CheckNewTable();
        // Получаем списко игр, записанных в бд
        ArrayList<String> GameList = new ArrayList<>();
        try {
            GameList = GameListDB.GameListDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Тест (Get)");
        request.setAttribute("GameList", GameList);
        request.getRequestDispatcher("/dataBase.jsp").forward(request, response);
    }

}