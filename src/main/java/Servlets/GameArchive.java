package Servlets;

import HelpMethods.WriteRead;
import JSON.ReadJSON;
import XML.ReadXML;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GameArchive extends HttpServlet {

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        int number;
        // Получаем список файлов, в которых содержаться данные об играх
        String[] listFiles = ArrayUtils.addAll(
                WriteRead.Read.ListFiles("xml"),
                WriteRead.Read.ListFiles("json")
        );
         if (listFiles==null){
             request.setAttribute("game","Нет сыгранных игр");
             getServletContext().getRequestDispatcher("/gameArchive.jsp").forward(request, response);
             doPost(request,response);
         }

        // Передаем этот список в одноименное jsp
        request.setAttribute("ListFiles",listFiles);
        // Получаем число в переменную, какой файл хотим прочитать (если оно пустое,
        // присваевам переменной невозможно значение (кол-во чего либо не может быть ниже 0)
        if (!request.getParameter("number").equals("")){
            number = Integer.parseInt(request.getParameter("number"))-1;
        } else {
            number = -1;
        }
        // Если число входит в границы кол-ва файлов, то печатаем данные об игре, содержащиеся в этом файле
        if ((number<=listFiles.length)&(number>=0)){

            // json или xml файл мы хотим прочитать
            WriteRead.Read read;
            if (listFiles[number].contains(".json")){
                read = new ReadJSON();
                request.setCharacterEncoding("UTF-8");
            } else {
                read = new ReadXML();
                request.setCharacterEncoding("windows-1251");
            }

            String b = read.Read(listFiles[number]);
            request.setAttribute("game",b);
            getServletContext().getRequestDispatcher("/gameArchive.jsp").forward(request, response);
        // Иначе пишем ошибку, что введено неккоректное число
        } else {
            request.setAttribute("game","Неккоректное число");
            getServletContext().getRequestDispatcher("/gameArchive.jsp").forward(request, response);
        }

    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("game","");
        String[] listFiles = ArrayUtils.addAll(
                WriteRead.Read.ListFiles("xml"),
                WriteRead.Read.ListFiles("json")
        );
        request.setAttribute("ListFiles",listFiles);
        getServletContext().getRequestDispatcher("/gameArchive.jsp").forward(request, response);
    }

}
