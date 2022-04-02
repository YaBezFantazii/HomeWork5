<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<div id="main">
    <form action="playerNick" method="get">
        <input type="submit" value="Играть"/>
    </form>
    <form action="rating" method="post">
        <input type="submit" value="Общий рейтинг"/>
    </form>
    <form action="gameArchive" method="get">
        <input type="submit" value="Записанные игры"/>
    </form>
    <form action="DataBase" method="get">
        <input type="submit" value="Данные из БД"/>
    </form>
</div>
</body>
</html>