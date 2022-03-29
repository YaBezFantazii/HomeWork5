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
    <form action="playerNick" method="post">
                <p>Введите имена игроков</p>
                <p>Игрок1:</p>
                <input type="text" name="nick1"/><br/><br/>
                <p>Игрок2:</p>
                <input type="text" name="nick2"/><br/><br/>
                <input type="submit" value="Ввод"/>
    </form>
    <%
                 String error = (String) request.getAttribute("error");
                 if (error==null){error="";}
                 out.println( "<br>"+error );
    %>

</div>
</body>
</html>