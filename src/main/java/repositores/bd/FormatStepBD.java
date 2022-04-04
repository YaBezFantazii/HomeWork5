package repositores.bd;

import java.util.ArrayList;

public class FormatStepBD {

    // Метод для преобразования шагов игры (в бд он записаны как String) в массив ArrayList для запись в класс GameList
    public static ArrayList<Integer> FormatStepBD (String steps){
        ArrayList<Integer> inte = new ArrayList<>();
        char[] chars = steps.toCharArray();
        for (int i=0;i<chars.length;i++){
            inte.add(Integer.parseInt(String.valueOf(chars[i])));
        }
        return inte;
    }

    // Перегруженный метод для преобразования шагов игры (в GameList они записаны как ArrayList)
    // в единую строку для записи в БД
    public static String FormatStepBD (ArrayList<Integer> steps){
        String result = "";
        for (int i=0;i<steps.size();i++){
            result += steps.get(i).toString();
        }
        return result;
    }
}
