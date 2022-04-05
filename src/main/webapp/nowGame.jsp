<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<div id="main">
    <form action="http://localhost:8080/gameplay" method="get">
        <input type="submit" value="Назад"/>
    </form>
    <form action="playerNick" method="get">
            <input type="submit" value="Новая игра"/>
    </form>
    <form>
            <div>Пример:</div>
            <div>| 1 | | 2 | | 3 |</div>
            <div>| 4 | | 5 | | 6 |</div>
            <div>| 7 | | 8 | | 9 |</div>
    </form>
    <%
                     String name1 = (String) request.getAttribute("nick1");
                     String name2 = (String) request.getAttribute("nick2");
                     out.println("<br>Player1: "+name1+"<br>");
                     out.println("Player2: "+name2);
    %>
    <form action="nowGame" method="post">
                <p>Введите номер ячейки</p>
                <input type="number" name="nowGame"/><br/><br/>
                <input type="submit" value="Ввод"/>
    </form>
    <%
                     String field = (String) request.getAttribute("field");
                        out.println(field+"<br>");

    %>
    <%
                 String error = (String) request.getAttribute("error");
                 out.println("<h3>"+ error +"</h3>");

    %>

</div>
</body>
</html>