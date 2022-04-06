<%@ page import="model.GameList" %>
<%@ page import="repositores.bd.DeleteBD"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<div id="main">
    <form action="http://localhost:8080/gameplay" method="post">
        <input type="submit" value="Назад"/>
    </form>
    <form action="http://localhost:8080/gameplay/deleteBD.jsp" method="post">
            <input type="submit" value="Очистить базу данных"/>
        </form>
    <form action="dataBase" method="post">
            <INPUT TYPE="radio" name="command" value="1"/>Общий рейтинг
            <p>Введите номер игры, который указан перед скобками:</p>
            <input type="number" name="id"/><br/><br/>
            <input type="submit" value="Принять"/>
            <div>Номер игры - игрок1 - игрок2</div>
            <div>-------------------------</div>
        </form>
        <%
                     ArrayList<String> GameList = (ArrayList<String>) request.getAttribute("GameList");
                     if (!GameList.isEmpty()){
                              for (int i=0;i<GameList.size();i++) {
                                  out.println(GameList.get(i)+"<br>");
                               }
                       }

                     if (request.getAttribute("rating")!="") {
                        String rating = (String) request.getAttribute("rating");
                        out.println("<h3>"+ rating +"</h3>");
                      }

        %>
        <%
             String game = (String) request.getAttribute("game");
             out.println( "<br>"+game );
        %>
</div>
</body>
</html>