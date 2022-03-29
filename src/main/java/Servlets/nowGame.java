package Servlets;

import HelpMethods.PrintArchive;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import Class.GameList;
import HelpMethods.WriteRead;
import JSON.WriteJSON;
import ProcessGame.CheckWin;
import ProcessGame.PrintGame;
import Rating.WriteRating;
import XML.WriteXML;

public class nowGame extends HttpServlet {

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        request.setCharacterEncoding("UTF-8");

        // Если сессии не существует, то перекидываем пользователя на страницу ввода имен
        if (session.getAttribute("GameList")==null){
            request.setAttribute("error","Сначала введите имена пользователей");
            getServletContext().getRequestDispatcher("/playerNick.jsp").forward(request, response);

        } else {
            request.setAttribute("field","");
            request.setAttribute("error","");
            // Получаем в объект Game данные об игре из сессии, в которой хранится объект того же класса
            GameList Game = (GameList) session.getAttribute("GameList");

            request.setAttribute("nick1",Game.getNickName1());
            request.setAttribute("nick2",Game.getNickName2());
            // cell принимает из jsp число, куда игрок хочет поставить X или 0
            int cell;
            // Если поле ввода пустое или нулевое, то присаеваем cell невозможное значение (cell может быть
            // от 1 до 9, по кол-ву ячеек поля)
            if (request.getParameter("nowGame").equals("")|request.getParameter("nowGame")==null){
                cell= -100;
            } else {
                cell = Integer.parseInt(request.getParameter("nowGame"));
            }
            // Проверяем что cell может быть только от 1 до 9, иначе пишем ошибку
            if (cell>=1&&cell<=9) {
                for (int i=0;i<Game.getCell().size();i++){
                    // Если в выбранную ячейку уже поставлен Х или О, то пишем ошибку и перезагружаем страницу
                    if (Game.getCellId(i)==cell){
                        request.setAttribute("error","Неккоректные данные");
                        request.setAttribute("field", PrintGame.PrintGame(Game));
                        getServletContext().getRequestDispatcher("/nowGame.jsp").forward(request, response);
                        doPost(request,response);
                    }
                }
                // Если все в порядке то записываем ход в объект Game, и обновляем данные в сессии
                Game.setCellId(cell);
                session.setAttribute("GameList",Game);

                // Проверка на победу (происходит каждый ход)
                // setWin=0 - победа 1 игрока, setWin=1 - победа 2 игрока, setWin=2 - ничья
                if (CheckWin.CheckWin(Game)){
                    if (Game.getCell().size()%2!=0) {
                        Game.setWin(0);
                    } else {
                        Game.setWin(1);
                    }
                    // Если размер массива cell объекта Game равен 9, и проверка на победу не пройдена,
                    // значит это ничья
                } else if (Game.getCell().size()==9){
                    Game.setWin(2);
                }
            } else if (cell!=-100){
                request.setAttribute("error","Неккоректные данные");
            }
            // Если переменная win в объекте класса GameList не равна 3 (тогда она равна от 0 до 2),
            // то записываем данные об игре в общий рейтинг, json и xml файлы, обращаемся в resultGame.jsp,
            // где печатаем все шаги игры, очищаем сессию
            if (Game.getWin()!=3){
                request.setAttribute("game", PrintArchive.Print(Game));

                WriteRead.Write writeJson = new WriteJSON();
                WriteRead.Write writeRating = new WriteRating();
                WriteRead.Write writeXML = new WriteXML();
                writeRating.Write(Game);
                String json = writeJson.Write(Game);
                String xml = writeXML.Write(Game);

                session.removeAttribute("GameList");

                request.setAttribute("json",json);
                request.setAttribute("xml",xml);
                getServletContext().getRequestDispatcher("/resultGame.jsp").forward(request, response);
                // Если победы еще нет (getWin=3), то продолжаем игру
            } else {
                request.setAttribute("field", PrintGame.PrintGame(Game));
                getServletContext().getRequestDispatcher("/nowGame.jsp").forward(request, response);
            }
        }
    }



    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name1 = request.getParameter("nick1");
        String name2 = request.getParameter("nick2");
        getServletContext().getRequestDispatcher("/nowGame.jsp?nick1="+name1+"&nick2="+name2).forward(request, response);
    }

}