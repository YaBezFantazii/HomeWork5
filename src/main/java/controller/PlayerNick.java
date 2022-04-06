package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import exceptions.PlayerNickLengthException;
import model.GameList;

public class PlayerNick extends HttpServlet {

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        // Получаем имена игроков из одноименного jsp
        String name1 = request.getParameter("nick1");
        String name2 = request.getParameter("nick2");
        GameList GameList = new GameList();

        try {
            GameList.setNickName(name1,name2);
            // Если все в порядке, то переходим в игре, передавая данные об именах игроков
            System.out.println("Check");
            request.setAttribute("error","");
            request.setAttribute("nick1",name1);
            request.setAttribute("nick2",name2);
            request.setAttribute("field","");
            HttpSession session = request.getSession();
            // Если сессия уже существует, то удаляем ее.
            if (session.getAttribute("GameList")!=null){
                System.out.println("Удаляем старую, создаем новую сессию (Post PlayerNick)"); // sout для тестов
                session.removeAttribute("GameList");
            }
            // Создаем новую сессию (в ней содержиться объект класса GameList, которые хранит данные о текущей игре)
                session = request.getSession();
                session.setAttribute("GameList",GameList);
                System.out.println("Переходим в игру (Post PlayerNick)"); // sout для тестов
            // Переходим к игре (nowGame.jsp)
                response.sendRedirect("/gameplay/nowGame");
            } catch (PlayerNickLengthException e) {
                System.out.println(e);
                e.printStackTrace();
                request.setAttribute("error",e.getMessage());
                request.getRequestDispatcher("/playerNick.jsp").forward(request, response);
         }
        }


    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("error"," ");
        request.getRequestDispatcher("/playerNick.jsp").forward(request, response);
        doPost(request,response);
    }

}
