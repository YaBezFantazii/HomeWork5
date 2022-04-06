<%@ page import="repositores.bd.DeleteBD" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<div id="main">
    <form action="dataBase" method="get">
            <input type="submit" value="Назад"/>
    </form>
    <%
                    DeleteBD.DeleteBD();
                    out.println("Таблицы очищены");
        %>
</div>
</body>
</html>