package Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import Class.GameList;

public class playerNick extends HttpServlet {

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        // Получаем имена игроков из одноименного jsp
        String name1 = request.getParameter("nick1");
        String name2 = request.getParameter("nick2");

        // Если имена совпадают, или хотя бы 1 поле пустое, то пишем ошибку
        if (name1.equals(name2)||name1==""||name2==""){
            request.setAttribute("error","Неккоректные данные");
            getServletContext().getRequestDispatcher("/playerNick.jsp").forward(request, response);

        } else {
            // Если все в порядке, то переходим в игре, передавая данные об именах игроков
            request.setAttribute("error","");
            request.setAttribute("nick1",name1);
            request.setAttribute("nick2",name2);
            request.setAttribute("field","");
            HttpSession session = request.getSession(false);
            // Если сессия уже существует, то удаляем ее.
            if (session.getAttribute("GameList")!=null){
                session.removeAttribute("GameList");
            }
            // Создаем новую сессию (в ней содержиться объект класса GameList, которые хранит данные о текущей игре)
                session = request.getSession();
                GameList GameList = new GameList();
                GameList.setNickName(name1,name2);
                session.setAttribute("GameList",GameList);
            }
            // Переходим к игре (nowGame.jsp)
            getServletContext().getRequestDispatcher("/nowGame.jsp").forward(request, response);
        }


    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("error"," ");
        getServletContext().getRequestDispatcher("/playerNick.jsp").forward(request, response);
    }

}
