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
    <%
                 String json = (String) request.getAttribute("json");
                 String xml = (String) request.getAttribute("xml");
                 String result = (String) request.getAttribute("result");
                 out.println( "<br>"+"Файл json сохранен по пути: "+json+"<br>" );
                 out.println( "Файл xml сохранен по пути: "+xml+"<br>" );
                 out.println( "Файл Result.txt расположен по пути: "+result+"<br>" );

                 String game = (String) request.getAttribute("game");
                 out.println( "<br>"+game );
        %>
</div>
</body>
</html>