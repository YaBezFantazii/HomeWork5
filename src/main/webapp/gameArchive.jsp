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
    <form action="gameArchive" method="post">
            <p>Введите номер файла, который указан перед скобками:</p>
            <input type="number" name="number"/><br/><br/>
            <input type="submit" value="Ввод"/>
        </form>
        <%
                     String[] ListFiles = (String[]) request.getAttribute("ListFiles");
                     if (ListFiles!=null){
                        for (int i=0;i<ListFiles.length;i++){
                             out.println(+(i+1)+") "+ ListFiles[i]+"<br>");
                        }
                     }
        %>
        <%
             String game = (String) request.getAttribute("game");
             out.println( "<br>"+game );
        %>
</div>
</body>
</html>