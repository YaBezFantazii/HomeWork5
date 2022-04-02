package Servlets;

import HelpMethods.WriteRead;
import DataBase.ReadBD;
import DataBase.CheckNewTable;

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
        ArrayList<String> GameList = new ArrayList<>();
        try {
            GameList = WriteRead.Read.GameListDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("GameList", GameList);

        String id = request.getParameter("id");
        WriteRead.Read read = new ReadBD();
        String b = read.Read(id);
        request.setAttribute("game",b);
        getServletContext().getRequestDispatcher("/dataBase.jsp").forward(request, response);

    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setAttribute("game", "");
        CheckNewTable.CheckNewTable();
        ArrayList<String> GameList = new ArrayList<>();
        try {
            GameList = WriteRead.Read.GameListDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("GameList", GameList);
        getServletContext().getRequestDispatcher("/dataBase.jsp").forward(request, response);
    }

}