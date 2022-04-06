package controller;

import repositores.WriteRead;
import repositores.json.ReadJSON;
import repositores.xml.ReadXML;
import utils.*;
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
                ListFiles.ListFiles("xml"),
                ListFiles.ListFiles("json")
        );
         if (listFiles==null){
             request.setAttribute("game","Нет сыгранных игр");
             request.getRequestDispatcher("/gameArchive.jsp").forward(request, response);
             doPost(request,response);
         }

        // Передаем этот список в одноименное jsp
        request.setAttribute("ListFiles",listFiles);
        // Получаем число в переменную, какой файл хотим прочитать (если оно пустое,
        // присваевам переменной невозможно значение (кол-во чего либо не может быть ниже 0)
        if (!request.getParameter("number").equals("")){
            number = Integer.parseInt(request.getParameter("number"))-1;
        } else {
            System.out.println("Не выбран файл (Post GameArchive)"); // sout для тестов
            number = -1;
        }
        // Если число входит в границы кол-ва файлов, то печатаем данные об игре, содержащиеся в этом файле
        if ((number<=listFiles.length)&(number>=0)){

            // json или xml файл мы хотим прочитать
            WriteRead.ReadFile read;
            if (listFiles[number].contains(".json")){
                read = new ReadJSON();
                System.out.println("читаем json (Post GameArchive)"); // sout для тестов
                System.out.println(System.getProperty("user.dir")+"\\json\\"+listFiles[number]); // sout для тестов
                request.setCharacterEncoding("UTF-8");
            } else {
                read = new ReadXML();
                System.out.println("Читаем xml (Post GameArchive)"); // sout для тестов
                System.out.println(System.getProperty("user.dir")+"\\xml\\"+listFiles[number]); // sout для тестов
                request.setCharacterEncoding("windows-1251");
            }

            String b = PrintArchive.Print(read.Read(listFiles[number]));
            request.setAttribute("game",b);
            request.getRequestDispatcher("/gameArchive.jsp").forward(request, response);
        // Иначе пишем ошибку, что введено неккоректное число
        } else {
            request.setAttribute("game","Неккоректное число");
            System.out.println("Неккоректное число");
            request.getRequestDispatcher("/gameArchive.jsp").forward(request, response);
        }

    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("game","");
        // Получаем список файлов, в которых содержаться данные об играх (xml, json)
        String[] listFiles = ArrayUtils.addAll(
                ListFiles.ListFiles("xml"),
                ListFiles.ListFiles("json")
        );
        request.setAttribute("ListFiles",listFiles);
        System.out.println("Тест (Get GameArchive)"); // sout для тестов
        request.getRequestDispatcher("/gameArchive.jsp").forward(request, response);
    }
}
