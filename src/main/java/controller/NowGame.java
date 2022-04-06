package controller;

import exceptions.CellCheckException;
import utils.PrintArchive;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import model.GameList;
import repositores.WriteRead;
import repositores.json.WriteJSON;
import services.CheckWin;
import services.PrintGame;
import repositores.rating.WriteRating;
import repositores.xml.WriteXML;
import repositores.bd.WriteBD;

public class NowGame extends HttpServlet {

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        boolean check=true;

        // Если сессии не существует, то перекидываем пользователя на страницу ввода имен
        if (session.getAttribute("GameList")==null){
            request.setAttribute("error","Сначала введите имена пользователей");
            System.out.println("Не введены имена игроков, переходим на страницу выбора (Post NowGame)"); // sout для тестов
            response.sendRedirect("/gameplay/playerNick.jsp");

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

            try {
                // Если в cell некорректные данные, то выбросится catch
                Game.setCellId(cell);
                // Если все в порядке то записываем ход в объект Game, и обновляем данные в сессии
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
            } catch (CellCheckException e) {
                if (cell!=-100){request.setAttribute("error",e.getMessage());}
                request.setAttribute("field", PrintGame.PrintGame(Game));
                System.out.println("Выбранная ячейка занята (Post NowGame"); // sout для тестов
                request.getRequestDispatcher("/nowGame.jsp").forward(request, response);
            }
            // Если переменная win в объекте класса GameList не равна 3 (тогда она равна от 0 до 2),
            // то записываем данные об игре в общий рейтинг, json и xml файлы, обращаемся в resultGame.jsp,
            // где печатаем все шаги игры, очищаем сессию
            if (Game.getWin()!=3){
                request.setAttribute("game", PrintArchive.Print(Game));

                WriteRead.WriteFile writeJson = new WriteJSON();
                WriteRead.Write writeRating = new WriteRating();
                WriteRead.WriteFile writeXML = new WriteXML();
                WriteRead.Write writeBD = new WriteBD();
                String json = writeJson.Write(Game);
                String xml = writeXML.Write(Game);
                writeRating.Write(Game);
                writeBD.Write(Game);
                String result = System.getProperty("user.dir")+"\\"+"result.txt";

                session.removeAttribute("GameList");

                request.setAttribute("json",json);
                request.setAttribute("xml",xml);
                request.setAttribute("result",result);
                System.out.println("Игра успешно закончена: GameList.win="+Game.getWin()); // sout для тестов
                request.getRequestDispatcher("/resultGame.jsp").forward(request, response);

                // Если победы еще нет (getWin=3), то продолжаем игру
            } else {
                request.setAttribute("field", PrintGame.PrintGame(Game));
                request.getRequestDispatcher("/nowGame.jsp").forward(request, response);
            }
        }
    }



    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        // Если сессии не существует, то перекидываем пользователя на страницу ввода имен
        if (session.getAttribute("GameList")==null){
            System.out.println("Не введены имена игроков, переходим на страницу выбора (Get NowGame)"); // sout для тестов
            response.sendRedirect("/gameplay/playerNick.jsp");
        } else {
            request.setAttribute("field","");
            request.setAttribute("error","");
            // Получаем в объект Game данные об игре из сессии, в которой хранится объект того же класса
            GameList Game = (GameList) session.getAttribute("GameList");
            request.setAttribute("nick1",Game.getNickName1());
            request.setAttribute("nick2",Game.getNickName2());
            request.setAttribute("field", PrintGame.PrintGame(Game));
            System.out.println("Переходим к игре (Get NowGame"); // sout для тестов
            request.getRequestDispatcher("/nowGame.jsp").forward(request, response);
            doPost(request,response);
        }
    }

}