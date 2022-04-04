package controller;

import repositores.WriteRead;
import repositores.rating.ReadRating;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Rating extends HttpServlet {

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Передаем данные из файла Result.txt (общий рейтинг) в одноименный jsp
        WriteRead.Read read = new ReadRating();
        request.setAttribute("rating", read.Read());
        request.getRequestDispatcher("/rating.jsp").forward(request, response);
    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Передаем данные из файла Result.txt (общий рейтинг) в одноименный jsp
        WriteRead.Read read = new ReadRating();
        request.setAttribute("rating", read.Read());
        request.getRequestDispatcher("/rating.jsp").forward(request, response);
    }

}
